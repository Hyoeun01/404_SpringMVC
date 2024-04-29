package org.zerock.b01.service;

import org.zerock.b01.dto.ReplyDTO;

public interface ReplyService {
  // 댓글 등록
  Long register(ReplyDTO replyDTO);
  // 댓글 읽기
  ReplyDTO read(Long rno);
  // 댓글 수정
  void modify(ReplyDTO replyDTO);
  // 댓글 삭제
  void remove(Long rno);
}
