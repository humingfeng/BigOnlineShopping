package cn.bos.service;

import cn.bos.common.pojo.EasyUITreeNode;

import java.util.List;

public interface ItemCatService {

    public List<EasyUITreeNode> getCatList(long parentId);
}
