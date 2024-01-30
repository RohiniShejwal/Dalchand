package com.BikkadIT.ProjectByDurgesh.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.BikkadIT.ProjectByDurgesh.entities.Category;
import com.BikkadIT.ProjectByDurgesh.entities.Post;
import com.BikkadIT.ProjectByDurgesh.entities.User;
import com.BikkadIT.ProjectByDurgesh.exceptions.ResourceNotFoundException;
import com.BikkadIT.ProjectByDurgesh.payloads.PostDto;
import com.BikkadIT.ProjectByDurgesh.payloads.PostResponse;
import com.BikkadIT.ProjectByDurgesh.repositories.CategoryRepository;
import com.BikkadIT.ProjectByDurgesh.repositories.PostRepository;
import com.BikkadIT.ProjectByDurgesh.repositories.UserRepository;
import com.BikkadIT.ProjectByDurgesh.services.PostService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

// create post
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		log.info("Initiating the dao call for create post");
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post save = this.postRepository.save(post);
		PostDto postDto2 = this.modelMapper.map(save, PostDto.class);
		log.info("Completing the dao call for create post");
		return postDto2;
	}

// update post
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		log.info("Initiating the dao call for update post with postId{} :", postId);
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		post.setAddedDate(postDto.getAddedDate());

		Post save = this.postRepository.save(post);
		PostDto postDto2 = this.modelMapper.map(save, PostDto.class);
		log.info("Completing the dao call for update post with postId{} :",postId);
		return postDto2;
	}

// delete post
	@Override
	public void deletePost(Integer postId) {
		log.info("Initiating the dao call for delete post with postId{} :", postId);
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		log.info("Completing the dao call for delete post with postId{} :", postId);
		this.postRepository.delete(post);
	}

// get all post
	@Override
	public PostResponse gelAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		log.info("Initiating the dao call for get all post");
      Sort sort = null;
		
		if(sortDir.equalsIgnoreCase("asc")) {
			
			sort=sort.by(sortBy).ascending();
			
		}else 
		{
			
			sort=sort.by(sortBy).descending();
		}
		
		Pageable p=PageRequest.of(pageNumber, pageSize, sort);
		
		 Page<Post> findAll = this.postRepository.findAll(p);
		 List<Post> allposts = findAll.getContent();
		 
		List<PostDto> postDtos = findAll.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		log.info("Completing the dao call for get all post");
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(findAll.getNumber());
		postResponse.setPageSize(findAll.getSize());
		postResponse.setTotalElements(findAll.getTotalElements());
		postResponse.setTotalPage(findAll.getTotalPages());
		postResponse.setLastpage(findAll.isLast());
		
		return postResponse;
	}

// get post by Id
	@Override
	public PostDto getPostById(Integer postId) {
		log.info("Initiating the dao call for get post");
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postid", postId));
		PostDto postDto = this.modelMapper.map(post, PostDto.class);
		log.info("Completing the dao call for get post");
		return postDto;
	}

//	// get post by category
//	@Override
//	public List<PostDto> getPostsByCategory(Integer categoryId) {
//	log.info("Initiating the dao call for get post with category{} :", categoryId);	
//	Category category = this.categoryRepository.findById(categoryId)
//				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
//		List<Post> posts = this.postRepository.FindByCategory(category);
//		List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
//				.collect(Collectors.toList());  
//	log.info("Completing the dao call for get post with category{} :", categoryId);	
//	return postDtos;
//	}

// get post by user
	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		log.info("Initiating the dao call for get post with user{} :", userId);
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		List<Post> posts = this.postRepository.findByUser(user);
		List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		log.info("Completing the dao call for get post with user{} :", userId);
		return postDtos;
	}

// search post
	@Override
	public List<Post> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
}
