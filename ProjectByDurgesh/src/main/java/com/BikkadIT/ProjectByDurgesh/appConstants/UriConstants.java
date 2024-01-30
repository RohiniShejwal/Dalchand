package com.BikkadIT.ProjectByDurgesh.appConstants;

public class UriConstants {
	
	// user
	public static final String REQUEST_MAPPING_USER ="/api/users/";
	public static final String USER_SLASH_URI ="/";
    public static final String USERS_ID="{userid}";

    //category
    public static final String REQUEST_MAPPING_CATEGORY ="/api/category/";
    public static final String CATEGORY_SLASH_URI ="/";
    public static final String CATEGORYS_ID="{categoryId}";
    
    // post
    public static final String REQUEST_MAPPING_POST ="/api/";
	public static final String POST_URI="post/";
	public static final String POST_ID="{postId}";
	public static final String USER_ID ="user/{userId}";
	public static final String CATEGORY_ID ="/category/{categoryId}";
	public static final String POST_SLASH_URI ="getAll/";
	
	//image
	public static final String REQUEST_MAPPING_IMAGE ="/api/";
	public static final String IMAGE_URI="post";
	public static final String IMAGE_UPLOAD_URI="/image/upload/";
	public static final String IMAGE_URI1="/image";
	public static final String IMAGE_NAME="/{imageName}";
	
	// Comment
	public static final String REQUEST_MAPPING_COMMENT ="/api/";
	public static final String COMMENT_URI ="/comments";
	public static final String COMMENTID_URI ="comments/{commentId}";
	public static final String COMMENTID1_URI ="/{commentId}";
	public static final String COMMENT_SLASH_URI ="/";
	

	

}
