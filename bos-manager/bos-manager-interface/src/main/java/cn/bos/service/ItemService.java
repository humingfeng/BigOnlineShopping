package cn.bos.service;

import cn.bos.common.pojo.EasyUIDataGridResult;
import cn.bos.common.utils.E3Result;
import cn.bos.pojo.TbItem;

public interface ItemService {
    public TbItem getItemById(long itemId);

    public EasyUIDataGridResult getItemList(int page, int rows);

    public E3Result addItem(TbItem item, String desc);
}
