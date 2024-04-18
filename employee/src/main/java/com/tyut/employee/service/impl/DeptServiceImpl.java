package com.tyut.employee.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyut.employee.utils.PageUtils;
import com.tyut.employee.utils.Query;

import com.tyut.employee.dao.DeptDao;
import com.tyut.employee.entity.DeptEntity;
import com.tyut.employee.service.DeptService;


@Service("deptService")
public class DeptServiceImpl extends ServiceImpl<DeptDao, DeptEntity> implements DeptService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DeptEntity> page = this.page(
                new Query<DeptEntity>().getPage(params),
                new QueryWrapper<DeptEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 查询部门树形菜单
     */
    @Override
    public List<DeptEntity> listWithTree() {
        // 1.查出所有分类（数据库只查询一次，内存进行修改）
        List<DeptEntity> entities = baseMapper.selectList(null);
        // 2.组装分类
        return entities.stream().filter(deptEntity -> deptEntity.getPId() == 0) // 先过滤得到所有一级分类
                .map((dept) -> {
                    dept.setChildren(getChildrens(dept, entities)); // 递归得到一级分类的子部门
                    return dept;
                }).sorted((dept1, dept2) -> { // 部门排序,这里运算一定要进行非空判断
                    return ((dept1.getSort() == null ? 0 : dept1.getSort()) - (dept2.getSort() == null ? 0 : dept2.getSort()));
                }).collect(Collectors.toList());
    }

    /**
     * 根据id统计部门数
     */
    @Override
    public Integer getDeptNumById(int i) {
        QueryWrapper<DeptEntity> queryWrapper = new QueryWrapper<>();
        if(i != 0) {
            queryWrapper.eq("p_id", i);
        }
        return baseMapper.selectCount(queryWrapper);
    }

    /**
     * 根据部门ID查询部门名
     */
    @Override
    public String getDeptNameByDeptId(Integer deptId) {
        return baseMapper.selectOne(new QueryWrapper<DeptEntity>().eq("id", deptId)).getName();
    }

    /**
     * 递归查询子部门
     */
    private List<DeptEntity> getChildrens(DeptEntity root, List<DeptEntity> all) {
        return all.stream().filter(deptEntity -> root.getId().equals(deptEntity.getPId())) // 找到root的子部门
                .map(dept -> {
                    dept.setChildren(getChildrens(dept, all)); // 设置为子部门
                    return dept;
                }).sorted((dept1, dept2) -> { // 部门排序,这里运算一定要进行非空判断
                    return ((dept1.getSort() == null ? 0 : dept1.getSort()) - (dept2.getSort() == null ? 0 : dept2.getSort()));
                }).collect(Collectors.toList());
    }

}
