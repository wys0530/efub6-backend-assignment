package efub.assignment.community.board.controller;

import efub.assignment.community.board.dto.request.BoardCreateRequest;
import efub.assignment.community.board.dto.response.BoardCreateResponse;
import efub.assignment.community.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    //게시판 생성
    @PostMapping
    public ResponseEntity<BoardCreateResponse> createBoard(
            @RequestHeader("Auth-id") Long memberId,
            @Valid @RequestBody BoardCreateRequest request
    ) {
        BoardCreateResponse response = boardService.createBoard(memberId, request);
        return ResponseEntity
                .created(URI.create("/boards/" + response.getBoardId()))
                .body(response);
    }
}
