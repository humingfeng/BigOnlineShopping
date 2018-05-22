package cn.bos.service.impl;

import java.util.Date;
import java.util.List;

import cn.bos.common.pojo.EasyUIDataGridResult;
import cn.bos.common.utils.E3Result;
import cn.bos.common.utils.IDUtils;
import cn.bos.mapper.TbItemDescMapper;
import cn.bos.pojo.TbItemDesc;
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

    @Autowired
    private TbItemDescMapper itemDescMapper;

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

    @Override
    public E3Result addItem(TbItem item, String desc) {
        // 1、生成商品id
        long itemId = IDUtils.genItemId();
        // 2、补全TbItem对象的属性
        item.setId(itemId);
        //商品状态，1-正常，2-下架，3-删除
        item.setStatus((byte) 1);
        Date date = new Date();
        item.setCreated(date);
        item.setUpdated(date);
        // 3、向商品表插入数据
        itemMapper.insert(item);
        // 4、创建一个TbItemDesc对象
        TbItemDesc itemDesc = new TbItemDesc();
        // 5、补全TbItemDesc的属性
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);
        // 6、向商品描述表插入数据
        itemDescMapper.insert(itemDesc);
        // 7、E3Result.ok()
        return E3Result.ok();

    }

}