package com.fangshuo.stars.job;

import com.fangshuo.stars.domain.User;
import com.fangshuo.stars.resp.BlogListResp;
import com.fangshuo.stars.service.BlogService;
import com.fangshuo.stars.service.CircleBlogService;
import com.fangshuo.stars.service.CommentService;
import com.fangshuo.stars.service.UserService;
import com.fangshuo.stars.util.SnowFlake;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.mining.cluster.ClusterAnalyzer;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class DocJob {

    private static final Logger LOG = LoggerFactory.getLogger(DocJob.class);

    @Resource
    private UserService userService;

    @Resource
    private BlogService blogService;

    @Resource
    private CircleBlogService circleBlogService;

    @Resource
    private CommentService commentService;

    public String trimBlog(String content){
        String s = content.replaceAll("<.{1,8}>", "");
        return s;
    }

    /**
     * 每30秒更新电子书信息
     */
//    @Scheduled(cron = "*/60 * * * * ?")
//    public void cron() {
//
//        List<User> list = userService.list();
//
//        //定义聚类分析类
//        ClusterAnalyzer<String> analyzer = new ClusterAnalyzer<String>();
//        for (User user : list) {
//            List<String> userWordList = new ArrayList<>();
//            // 查找出该用户下的所有帖子
//            List<BlogListResp> blogListResps = blogService.myList(user.getId());
//            for (BlogListResp blog : blogListResps) {
//                //将用户的每条帖子进行分词，将名词放入词表中
//                List<String> wordList = new ArrayList<>();
//
//                //将帖子内容进行预处理
//                String trimBlog = trimBlog(blog.getContent());
//                List<Term> termList = StandardTokenizer.segment(trimBlog);
//
//                for (Term term : termList) {
//                    if (term.nature.equals(Nature.n) || term.nature.equals(Nature.nz)) {
//                        wordList.add(term.word);
//                    }
//                }
//                //提取出该贴子的关键词列表
//                List<String> keywordList = HanLP.extractKeyword(blog.getContent(), 5);
//                wordList.addAll(keywordList);
//                //将该帖子的所有关键词放入用户关键词列表中
//                userWordList.addAll(wordList);
//            }
//            String document = new String();
//            for (String word : userWordList) {
//                document = document.concat(",").concat(word);
//            }
//            //将该用户的信息放入聚类分析方法中
//            analyzer.addDocument(user.getName(), document);
//        }
//        //进行聚类分析，簇的数量为用户数/5
//        List<Set<String>> kmeans = analyzer.kmeans(list.size() / 5 + 1);
//        LOG.info("kmeans聚类结果：{}", kmeans);
////        List<Term> termList = StandardTokenizer.segment("我今天看的篮球赛也太棒了，还有今天踢足球好累");
////
////        List<String> wordList = new ArrayList<>();
////        for (Term term:
////             termList) {
////            if (term.nature.equals(Nature.n) || term.nature.equals(Nature.nz)){
////                wordList.add(term.word);
////            }
////        }
////        LOG.info("分词结果：{}", termList);
////        LOG.info("名词结果：{}", wordList);
////
////        String document = new String();
////        for (String word:
////             wordList) {
////            document.concat("," + word);
////        }
////        analyzer.addDocument("赵一", "流行, 流行, 流行, 流行, 流行, 流行, 流行, 流行, 流行, 流行, 蓝调, 蓝调, 蓝调, 蓝调, 蓝调, 蓝调, 摇滚, 摇滚, 摇滚, 摇滚");
////        analyzer.addDocument("钱二", "爵士, 爵士, 爵士, 爵士, 爵士, 爵士, 爵士, 爵士, 舞曲, 舞曲, 舞曲, 舞曲, 舞曲, 舞曲, 舞曲, 舞曲, 舞曲");
////        analyzer.addDocument("张三", "古典, 古典, 古典, 古典, 民谣, 民谣, 民谣, 民谣");
////        analyzer.addDocument("李四", "爵士, 爵士, 爵士, 爵士, 爵士, 爵士, 爵士, 爵士, 爵士, 金属, 金属, 舞曲, 舞曲, 舞曲, 舞曲, 舞曲, 舞曲");
////        analyzer.addDocument("王五", "流行, 流行, 流行, 流行, 摇滚, 摇滚, 摇滚, 嘻哈, 嘻哈, 嘻哈");
////        analyzer.addDocument("马六", "古典, 古典, 古典, 古典, 古典, 古典, 古典, 古典, 摇滚");
////
////        analyzer.addDocument("fyh", document);
////        List<Set<String>> kmeans = analyzer.kmeans(3);
////        LOG.info("kmeans结果：{}", kmeans);
////
////        String content = "我今天看的篮球赛也太棒了，还有今天踢足球好累！我都不知道这日子什么时候是个头，有没有陪我打游戏的啊";
////        List<String> keywordList = HanLP.extractKeyword(content, 5);
////        LOG.info("关键词提取结果：{}", keywordList);
////    }
//    }
}
