package daily.year2024.m5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * No.399 除法求值
 *
 * @author wjs 2024/5/25
 */
public class d25 {

    // 解法：使用并查集计算结果
    Map<String,String> parent;
    Map<String,Double> vs;
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        parent = new HashMap<>();
        vs = new HashMap<>();
        double[] res = new double[queries.size()];
        // 初始化两个集合
        for (List<String> lists : equations) {
            String x = lists.get(0),y = lists.get(1);
            parent.put(x,x);
            parent.put(y,y);
            vs.put(x,1.0);
            vs.put(y,1.0);
        }
        for (int i=0;i < equations.size();i++) {
            List<String> lists = equations.get(i);
            String x = lists.get(0),y = lists.get(1);
            String px = findParent(x), py = findParent(y);
            //合并集合
            if (!px.equals(py)) {
                if (py.equals(y)) {
                    parent.put(y,x);
                    vs.put(y,1/values[i]);
                } else {
                    Double vy = getVal(y);
                    parent.put(y,x);
                    vs.put(y,1/values[i]);
                    parent.put(py,y);
                    vs.put(py,1/vy);
                }
            }
        }
        for (int i=0;i < queries.size();i++) {
            List<String> query = queries.get(i);
            res[i] = countValue(query.get(0),query.get(1));
        }
        return res;
    }
    // 获取并查集的父节点
    private String findParent(String x) {
        String p = parent.get(x);
        if (p == null || "".equals(p)) {
            return "";
        }
        if (x.equals(p)) {
            return p;
        }
        // 扁平化并查集结构
        p = findParent(p);
        Double vx = getVal(x);
        vs.put(x,vx);
        parent.put(x,p);
        return p;
    }
    // 获取相对集合底数的倍数
    private Double getVal(String x) {
        String p = parent.get(x);
        Double v = vs.get(x);
        if (x.equals(p)) {
            return v;
        }
        return v * getVal(p);
    }
    // 计算 x/y 的值
    private Double countValue(String x, String y) {
        String px = findParent(x), py = findParent(y);
        if (px == null || "".equals(px) || py == null || "".equals(py)) {
            return -1.0;
        }
        if (!px.equals(py)) {
            return -1.0;
        }
        return getVal(x) / getVal(y);
    }
}
