package com.familytouch.data;

public class ServiceJsonObject {
	
	private String description, image, name, url;
	private int id, user_id;
	
	public ServiceJsonObject(String description, String image, String name, String url,
				int id, int user_id) {
		this.description = description;
		this.image = image;
		this.name = name;
		this.url = url;
		this.id = id;
		this.user_id = user_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	
}
