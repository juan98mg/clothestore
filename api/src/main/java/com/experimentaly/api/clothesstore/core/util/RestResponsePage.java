package com.experimentaly.api.clothesstore.core.util;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class RestResponsePage<T> extends PageImpl<T> {

    private static final long serialVersionUID = 3248189030448292002L;

    public RestResponsePage(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);

    }

    public RestResponsePage(List<T> content) {
        super(content);

    }


    public RestResponsePage() {
        super(new ArrayList<T>());
    }

}
