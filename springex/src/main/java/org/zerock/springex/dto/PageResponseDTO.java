package org.zerock.springex.dto;

import java.util.List;

public class PageResponseDTO<E> {
  private int page;
  private int size;
  private int total;

  //시작 페이지 번호
  private int start;
  //끝 페이지 번호
  private int end;

  //이전 페이지 존재여부
  private boolean prev;
  //다음 페이지 존재여부
  private boolean next;

  // dtoList 로 받기.
  private List<E> dtoList;

  // 생성자 만들기. 파라미터 3개짜리.
  // 페이지 계산 방법,
  // 예)
  // 기본, 1,2,3,4,5,6,7,8,9,10
  // 페이지 2: 2,3,4,5,6,7,8,9,10,11
  // 페이지 3: 3,4,5,6,7,8,9,10,11,12
  // 페이지 10:, 10,11,12,13,14,15,16,17,18,19

  // end 페이지 계산후, start 페이지 계산 해보기.




  public PageResponseDTO(PageRequestDTO pageRequestDTO,
                         List<E> dtoList, int total){
    this.page = pageRequestDTO.getPage();
    this.size = pageRequestDTO.getSize();

    this.total = total;
    this.dtoList = dtoList;

    // end 페이지 먼저 계산하기.
    // 예) 1/10 = 0.1 -> 1 => 10
    // 2 페이지
    // 예) 2/10 = 0.2 -> 1 => 10
    // 11 페이지
    // 예) 11/10 = 1.1 -> 2 => 20
    // 21 페이지
    // 예) 21/10 = 2.1 -> 3 => 30
    this.end = (int)(Math.ceil(this.page / 10.0))*10;

    // start , 시작 페이지 구하기.
    // 예) end = 30 , start = 30 - 9 = 21
    // 예) end = 20 , start = 20 - 9 = 11
    this.start = this.end - 9;

    // last 값이 없어서 , 생성후, end 멤버에 재사용하기.
    int last = (int) (Math.ceil(total/(double)size));


  }
}
