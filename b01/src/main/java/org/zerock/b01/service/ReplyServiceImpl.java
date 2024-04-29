package org.zerock.b01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.b01.domain.Reply;
import org.zerock.b01.dto.ReplyDTO;
import org.zerock.b01.repository.ReplyRepository;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService {
  // 의존성 주입, DI
  private final ReplyRepository replyRepository;
  private final ModelMapper modelMapper;
  @Override
  public Long register(ReplyDTO replyDTO) {
    // replyDTO -> 엔티티 클래스 타입으로 변환,
  Reply reply = modelMapper.map(replyDTO, Reply.class);
  Long rno = replyRepository.save(reply).getRno();

    return rno;
  }
}
