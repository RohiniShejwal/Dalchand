package com.BikkadIT.ProjectByDurgesh.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

//import com.BikkadIT.ProjectByDurgesh.entities.Category;
import com.BikkadIT.ProjectByDurgesh.entities.Post;
import com.BikkadIT.ProjectByDurgesh.entities.User;


public interface PostRepository extends JpaRepository<Post, Integer> {

	
	List<Post> findByUser(User user);

	//List<Post> FindByCategory(Category category);
	
}
