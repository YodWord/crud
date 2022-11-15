package com.solo.crud.web.service.comment;

import com.solo.crud.domain.Comment;
import com.solo.crud.domain.User;
import com.solo.crud.web.controller.DTO.CommentDTO;

public interface CommentService {

    public CommentDTO.commentCreateResponse save (CommentDTO.commentCreateRequest request, User user) throws Exception;

}
