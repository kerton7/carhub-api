package com.alituran.utils;

import com.alituran.dto.RestPageableRequest;
import com.alituran.dto.RestPageableResponse;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@UtilityClass
public class PageUtil {

    private  boolean isBlankOrNull(String string){
        return string == null || string.isEmpty()||string.trim().length()==0;
    }


    public  Pageable toPageable(RestPageableRequest request) {
        if(!isBlankOrNull(request.getColumnName())){
            Sort sort = request.isAsc()
                    ? Sort.by(Sort.Direction.ASC, request.getColumnName())
                    : Sort.by(Sort.Direction.DESC, request.getColumnName());
            return PageRequest.of(request.getPageNumber(), request.getPageSize(), sort);
        }
        return PageRequest.of(request.getPageNumber(), request.getPageSize());
    }

    public   <T>RestPageableResponse toRestPageableResponse(Page<?> page, List<T> content){
        return new RestPageableResponse(page.getPageable().getPageSize(),page.getPageable().getPageNumber()
                ,page.getTotalPages(),content);

    }

}
