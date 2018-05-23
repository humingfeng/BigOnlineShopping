package cn.bos.content.service;

import cn.bos.common.pojo.EasyUITreeNode;

import java.util.List;

public interface ContentCategoryService {

    public List<EasyUITreeNode> getContentCategoryList(long parentId);

}
