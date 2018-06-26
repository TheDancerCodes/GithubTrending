package com.thedancercodes.domain.model

// Creating the model representation of the domain layer.
// The data models represent the business rules of the app & match what we get back from the Github API.
class Project(val id: String, val name: String, val fullName: String,
              val starCount: String, val dateCreated: String,
              val ownerName: String, val ownerAvatar: String,
              val isBookmarked: Boolean) {
}