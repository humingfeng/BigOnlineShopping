package cn.bos.content.service;

import cn.bos.common.pojo.EasyUITreeNode;
import cn.bos.common.utils.E3Result;

import java.util.List;

public interface ContentCategoryService {

    public List<EasyUITreeNode> getContentCategoryList(long parentId);

    public E3Result addContentCategory(long parentId, String name);
}
