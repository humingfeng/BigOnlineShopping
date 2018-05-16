package cn.bos.service.impl;

import cn.bos.common.pojo.EasyUITreeNode;
import cn.bos.mapper.TbItemCatMapper;
import cn.bos.pojo.TbItemCat;
import cn.bos.pojo.TbItemCatExample;
import cn.bos.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;



    @Override
    public List<EasyUITreeNode> getCatList(long parentId) {

        //根据parentid查询节点
        TbItemCatExample example = new TbItemCatExample();

        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = itemCatMapper.selectByExample(example);

        // 2、转换成EasyUITreeNode列表。
        List<EasyUITreeNode> resultList = new ArrayList<>();

        for (TbItemCat tbItemCat : list) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbItemCat.getId());
            node.setText(tbItemCat.getName());
            node.setState(tbItemCat.getIsParent()?"closed":"open");
            //添加到列表
            resultList.add(node);
        }


        return resultList;
    }
}
