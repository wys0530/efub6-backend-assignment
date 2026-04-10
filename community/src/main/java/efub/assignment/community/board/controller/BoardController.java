package efub.assignment.community.board.controller;

import efub.assignment.community.board.dto.request.BoardCreateRequest;
import efub.assignment.community.board.dto.request.BoardOwnerUpdateRequest;
import efub.assignment.community.board.dto.response.BoardCreateResponse;
import efub.assignment.community.board.dto.response.BoardOwnerUpdateResponse;
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

    // 게시판 주인 변경
    @PatchMapping("/{boardId}/owner")
    public ResponseEntity<BoardOwnerUpdateResponse> changeBoardOwner(
            @RequestHeader("Auth-id") Long authMemberId,
            @PathVariable Long boardId,
            @Valid @RequestBody BoardOwnerUpdateRequest request
    ) {
        BoardOwnerUpdateResponse response =
                boardService.changeBoardOwner(authMemberId, boardId, request);

        return ResponseEntity.ok(response);
    }

}
