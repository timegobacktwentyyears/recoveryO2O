
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoubleField;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class TestSolrNew {
	
	//@Test
	public void test01() throws IOException{
		//创建索引文件存放目录         会在当前工程下建立index目录
		//Directory directory = FSDirectory.open(new File("./index"));
		Directory directory = FSDirectory.open(Paths.get("./index"));
		//定义分词器   定义标准分词器
		//Analyzer analyzer = new StandardAnalyzer();
		
		//切换中文分词器
		//Analyzer analyzer = new ChineseAnalyzer();
		
		Analyzer analyzer = new IKAnalyzer();
		//创建配置文件    定义版本号      
		//IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_4_10_2, analyzer);
		
		//引入新版本5.3.0后修改
		IndexWriterConfig conf = new IndexWriterConfig(analyzer);
		
		
		//创建索引文件
		IndexWriter indexWriter = new IndexWriter(directory, conf);
		//创建文档对象:
		Document document = new  Document();
		/**
		 * 参数名称:  
		 * id :属性字段名称      value值     Store 表示是否存储   yes表示存入
		 */
		document.add(new LongField("id", 5025991L, Store.YES));
		document.add(new TextField("title", "vivo X20 全面屏双摄拍照手机 4GB+64GB 磨砂黑 移动联通电信全网通4G手机 双卡双待 ", Store.YES));
		document.add(new TextField("sellPoint", "游戏免打扰，畅快体验，带你吃鸡、上王者！高清全面屏，尊享Hi-Fi音质，更有面部识别+指纹双解锁！", Store.YES));
		document.add(new DoubleField("price", 2988.00, Store.YES));
		document.add(new TextField("itemDesc", "分辨率：2160*1080分辨后置摄像头：2x1200万像素+500万像素（2400万感光单元）前置摄像头：2x1200万像素（2400万感光单元） 核      数：八核", Store.YES));
		//将索引文件写入到文档中
		indexWriter.addDocument(document);
		indexWriter.close();
	}
	
	@Test
	public void seracher() throws IOException{
		//Directory directory = FSDirectory.open(new File("./index"));
		Directory directory = FSDirectory.open(Paths.get("./index"));
		//创建检索文件
		
		//创建检索文件
		//IndexSearcher indexSearcher = new IndexSearcher(IndexReader.open(directory));
		
		//新版本文件
		IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(directory));
		
		
		//创建查询条件   Term表示分组
		Query query = new TermQuery(new Term("title", "手机"));
		//查询  results 表示最顶端的20条记录数
		TopDocs docs  = indexSearcher.search(query, 20);
		//访问总数
		System.out.println("数据访问的总数:"+docs.totalHits);
		//获取文章的得分
		for ( ScoreDoc scoreDoc : docs.scoreDocs) {
			System.out.println("获取文章的得分:"+scoreDoc.score);  //分数越高 越靠前
				int index = scoreDoc.doc; //获取索引值
				//获取文章内容     
				Document document = indexSearcher.doc(index);
				//输出文章内容
				System.out.println("标题:"+document.get("title"));
				System.out.println("卖点:"+document.get("sellPoint"));
				System.out.println("价格:"+document.get("price"));
				System.out.println("描述信息:"+document.get("itemDesc"));	
		}
		directory.close();
		
	}
	
	
	
	@Test	//搜索
	public void searcher() throws IOException{
		
		
	}
	
	
	
	
	
}
