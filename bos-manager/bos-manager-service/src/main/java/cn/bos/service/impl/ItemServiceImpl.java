package cn.bos.service.impl;

import java.util.List;

import cn.bos.common.pojo.EasyUIDataGridResult;
import cn.bos.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bos.mapper.TbItemMapper;
import cn.bos.pojo.TbItem;
import cn.bos.pojo.TbItemExample;
import cn.bos.pojo.TbItemExample.Criteria;

/**
 * 商品管理Service
 * <p>Title: ItemServiceImpl</p>
 * <p>Description: </p>
 * @version 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {

//    private Logger log = Logger.getLogger(ItemServiceImpl.class);

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem getItemById(long itemId) {
        //根据主键查询
        //TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
        TbItemExample example = new TbItemExample();
        Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andIdEqualTo(itemId);
        //设置分页信息
        PageHelper.startPage(1, 30);
        //执行查询
        List<TbItem> list = itemMapper.selectByExample(example);
        //取分页信息
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {

        //设置分页
        PageHelper.startPage(page,rows);

        //执行查询
        TbItemExample tbItemExample = new TbItemExample();
        List<TbItem> tbItems = itemMapper.selectByExample(tbItemExample);

//        log.debug("tbItems="+tbItems.size());

        //取分页信息
        PageInfo<TbItem> pageInfo = new PageInfo<>(tbItems);

        //创建返回结果对象
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(tbItems);


        return result;
    }

}