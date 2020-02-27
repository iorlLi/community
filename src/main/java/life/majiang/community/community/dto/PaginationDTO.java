package life.majiang.community.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    //上一页
    private boolean showPrevious;
    //首页
    private boolean showFirstPage;
    //尾页
    private boolean showEndPage;
    //下一页
    private boolean showNext;
    private Integer page;
    private Integer totalPage;
    private List<Integer> pages = new ArrayList<>();


    public void setPagition(Integer totalPage, Integer page) {
        this.page = page;
        this.totalPage = totalPage;
        //pages 信息
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        // 显示上一页
        showPrevious = page == 1 ? false : true;
        //展示第一页
        showFirstPage = pages.contains(1) ? false : true;

        //显示下一页
        showNext = page == totalPage ? false : true;
        //展示尾页
        showEndPage = pages.contains(totalPage) ? false : true;
    }
}
