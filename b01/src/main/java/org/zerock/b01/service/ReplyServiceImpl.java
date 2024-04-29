package org.zerock.b01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.b01.domain.Reply;
import org.zerock.b01.dto.ReplyDTO;
import org.zerock.b01.repository.ReplyRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService {
  // 의존성 주입, DI
  private final ReplyRepository replyRepository;
  private final ModelMapper modelMapper;

  // 댓글 등록
  @Override
  public Long register(ReplyDTO replyDTO) {
    // replyDTO -> 엔티티 클래스 타입으로 변환,
    Reply reply = modelMapper.map(replyDTO, Reply.class);
    Long rno = replyRepository.save(reply).getRno();

    return rno;
  }

  // 댓글 읽기
  @Override
  public ReplyDTO read(Long rno) {
    // 댓글 번호로 찾기 했고, 반환 타입은 옵션널
    Optional<Reply> replyOptional = replyRepository.findById(rno);
    // 옵션널 타입 -> 엔티티 클래스 타입으로 변환,
    Reply reply = replyOptional.orElseThrow();
    // 엔티티 클래스 타입 -> DTO 데이터 전달용으로 사용하는 타입으로 변환하기.
    return modelMapper.map(reply, ReplyDTO.class);
  }


  // 댓글 수정
  @Override
  public void modify(ReplyDTO replyDTO) {
// 댓글 번호로 찾기 했고, 반환 타입은 옵션널
    Optional<Reply> replyOptional = replyRepository.findById(replyDTO.getRno());
    // 옵션널 타입 -> 엔티티 클래스 타입으로 변환,
    Reply reply = replyOptional.orElseThrow();
    // 댓글 내용 수정하는 메서드
    reply.changeText(replyDTO.getReplyText());
    // 엔티티 클래스 타입 -> DTO 데이터 전달용으로 사용하는 타입으로 변환하기.
    replyRepository.save(reply);
  }

  // 댓글 삭제
  @Override
  public void remove(Long rno) {
    replyRepository.deleteById(rno);
  }
}
