package second;

import java.text.ParseException;
import java.util.List;
import first.record;
import first.message;
import first.news;
/**
 * 数据访问层的接口定义数据接口的方法
 * 
 */
public interface UserDaoInf {
    //定义一个查询方法
	List<record> selectRecord(String user_id,String start_date,String end_date);
	int adduser(String user_id,String name,String birthdate,String phone_number,String email_address,String credential,String user_password,int account_type);
	int addRecord(String record_id,String user_id,String url,String detect_date);
	int uploadRecordAlg(String record_id,String diagnose_result,String url);
	int uploadRecordDoc(String record_id,String doctor_id,String url,String doctor_result);
	int deleteRecord(String record_id);
	int deleteUser(String user_id);
	int addNews(String news_id,String news_title, String news_content, String news_time);
	int uploadNews(String news_title,String news_content);
	int deleteNews(String news_title);
	int addAns(String keyword,String answer,String ans_id);
	String searchAns(String keyword);
	int uploadAns(String keyword,String answer,String ans_id);
	int deleteAns(String ans_id);
	int addMessage(String sender_id,String receiver_id,String content,String time);
	int deleteMessage(String message_id);
	List<news> selectNews(String time);
	List<message> searchMessagebyTime(String user1_id,String user2_id,String start_date,String end_date);
	List<message> searchMessagebyNum(String user1_id,String user2_id,int n);
	
}