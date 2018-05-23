package cn.bos.content.service.impl;


import cn.bos.common.pojo.EasyUITreeNode;
import cn.bos.content.service.ContentCategoryService;
import cn.bos.mapper.TbContentCategoryMapper;
import cn.bos.pojo.TbContentCategory;
import cn.bos.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
     * @ProjectName:    内容分类管理
     * @Package:        cn.bos.content.service.impl
     * @ClassName:      ContentCategoryServiceImpl
     * @Description:    java类作用描述
     * @Author:         胡铭锋
     * @CreateDate:     2018/5/23 18:01
     * @Version:        1.0
     */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {



    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;


    @Override
    public List<EasyUITreeNode> getContentCategoryList(long parentId) {

        // 1、取查询参数id，parentId
        // 2、根据parentId查询tb_content_category，查询子节点列表。
        TbContentCategoryExample example = new TbContentCategoryExample();
        //设置查询条件
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        // 3、得到List<TbContentCategory>
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        // 4、把列表转换成List<EasyUITreeNode>ub
        List<EasyUITreeNode> resultList = new ArrayList<>();
        for (TbContentCategory tbContentCategory : list) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            node.setState(tbContentCategory.getIsParent()?"closed":"open");
            //添加到列表
            resultList.add(node);
        }
        return resultList;

    }

}
