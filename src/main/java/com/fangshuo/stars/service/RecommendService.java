package com.fangshuo.stars.service;

import com.fangshuo.stars.domain.Circle;
import com.fangshuo.stars.domain.User;
import com.fangshuo.stars.resp.BlogListResp;
import com.fangshuo.stars.resp.UserInfoResp;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.mining.cluster.ClusterAnalyzer;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RecommendService {

    private static final Logger LOG = LoggerFactory.getLogger(RecommendService.class);

    @Resource
    private UserService userService;

    @Resource
    private BlogService blogService;

    @Resource
    private CircleBlogService circleBlogService;

    @Resource
    private CircleService circleService;

    @Resource
    private CommentService commentService;

    public String trimBlog(String content){
        String s = content.replaceAll("<.{1,8}>", "");
        return s;
    }

    // 用户聚类
    public List<Set<String>> cluster(){

        List<User> list = userService.list();

        //定义聚类分析类
        ClusterAnalyzer<String> analyzer = new ClusterAnalyzer<String>();
        for (User user : list) {
            List<String> userWordList = new ArrayList<>();
            // 查找出该用户下的所有帖子
            List<BlogListResp> blogListResps = blogService.myList(user.getId());
            for (BlogListResp blog : blogListResps) {
                //将用户的每条帖子进行分词，将名词放入词表中
                List<String> wordList = new ArrayList<>();

                //将帖子内容进行预处理
                String trimBlog = trimBlog(blog.getContent());
                List<Term> termList = StandardTokenizer.segment(trimBlog);

                for (Term term : termList) {
                    if (term.nature.equals(Nature.n) || term.nature.equals(Nature.nz)) {
                        wordList.add(term.word);
                    }
                }
                //提取出该贴子的关键词列表
                List<String> keywordList = HanLP.extractKeyword(blog.getContent(), 5);
                wordList.addAll(keywordList);
                //将该帖子的所有关键词放入用户关键词列表中
                userWordList.addAll(wordList);
            }
            String document = new String();
            for (String word : userWordList) {
                document = document.concat(",").concat(word);
            }
            //将该用户的信息放入聚类分析方法中
            analyzer.addDocument(user.getName(), document);
        }
        //进行聚类分析，簇的数量为用户数/5
        List<Set<String>> kmeans = analyzer.kmeans(list.size() / 5 + 1);
        LOG.info("kmeans聚类结果：{}", kmeans);
        return kmeans;
    }

    public Set<String> getNameCluster(String name){
        List<Set<String>> result = cluster();
        for (Set<String> set:
             result) {
            for (String s : set) {
                if (s.equals(name)){
                    return set;
                }
            }
        }
        return null;
    }

    public List<UserInfoResp> getUserCluster(String name){
        Set<String> nameCluster = getNameCluster(name);
        List<UserInfoResp> userInfoRespList = new ArrayList<>();
        for (String recommendName : nameCluster) {
            if (!recommendName.equals(name)){
                Long userIdByName = userService.getUserIdByName(recommendName);
                UserInfoResp userInfo = userService.getUserInfo(userIdByName);
                userInfoRespList.add(userInfo);
            }
        }
        return userInfoRespList;
    }


    public List<Circle> getCircleCluster(String name){
        List<Circle> circleList = circleService.list();
        return circleList;
    }
}
