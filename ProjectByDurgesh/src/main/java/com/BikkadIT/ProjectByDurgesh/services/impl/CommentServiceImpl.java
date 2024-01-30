package com.BikkadIT.ProjectByDurgesh.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BikkadIT.ProjectByDurgesh.entities.Comment;
import com.BikkadIT.ProjectByDurgesh.entities.Post;
import com.BikkadIT.ProjectByDurgesh.exceptions.ResourceNotFoundException;
import com.BikkadIT.ProjectByDurgesh.payloads.CategoryDto;
import com.BikkadIT.ProjectByDurgesh.payloads.CommentDto;
import com.BikkadIT.ProjectByDurgesh.repositories.CommentRepository;
import com.BikkadIT.ProjectByDurgesh.repositories.PostRepository;
import com.BikkadIT.ProjectByDurgesh.services.CommentServiceI;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommentServiceImpl implements CommentServiceI {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	
// create comment
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		log.info("Initiating the dao call for create comment");
		Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment = this.commentRepository.save(comment);
		CommentDto commentDto2 = this.modelMapper.map(savedComment, CommentDto.class);
		log.info("Completing the dao call for create comment");
		return commentDto2;
	}

	
// delete comment 
	
	@Override
	public void deleteComment(Integer commentId) {
		log.info("Initiating the dao call for deleting comment with commentId{} :", commentId);
		Comment comment = this.commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "CommentId", commentId));
		log.info("Completing the dao call for deleting comment with commentId{} :", commentId);
		this.commentRepository.delete(comment);
	}


	@Override
	public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
		log.info("Initiating the dao call for updating comment with commentId{} :", commentId);
		Comment comment = this.commentRepository.findById(commentId)
				.orElseThrow(()-> new ResourceNotFoundException("Comment", "CommentId", commentId));
		
		comment.setContent(commentDto.getContent());
		
		Comment save = this.commentRepository.save(comment);
		CommentDto commentDto2 = this.modelMapper.map(save, CommentDto.class);
		log.info("Completing the dao call for updating comment with commentId{} :", commentId);
		return commentDto2;
	}

	@Override
	public List<CommentDto> getComments() {
		log.info("Initiating the dao call for get all comment");
		List<Comment> findAll = this.commentRepository.findAll();
		List<CommentDto> collect = findAll.stream().map(comment -> this.modelMapper.map(comment, CommentDto.class))
		.collect(Collectors.toList());
		log.info("Completing the dao call for get all comment");
		return collect;
	}
	
	@Override
	public CommentDto getCommentById(Integer commentId) {
		log.info("Initiating the dao call for get single comment");
		Comment comment = this.commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "commentId", commentId));
		log.info("Completing the dao call for get single comment");
		return this.modelMapper.map(comment, CommentDto.class);
	}
}
