package com.solo.crud.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.solo.crud.web.controller.DTO.BoardDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Entity
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNo;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @Column(length = 254, nullable = false)
    private String boardTitle;

    @Column(length = 254, nullable = false)
    private String boardContents;

    private Timestamp boardCreateDate;

    public static Board createBoard(BoardDTO.createBoardRequest request, User user){
        Board board = new Board();

        board.user = user;
        board.boardTitle = request.getBoard_title();
        board.boardContents = request.getBoard_contents();
        board.boardCreateDate = Timestamp.valueOf(LocalDateTime.now());

        return board;
    }

}
