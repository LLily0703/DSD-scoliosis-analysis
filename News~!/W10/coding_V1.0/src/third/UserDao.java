package third;
import java.io.File;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import first.record;
import first.news;
import first.message;
import second.UserDaoInf;

/**
 * 
 * @author wl 数据访问接口
 */
public class UserDao implements UserDaoInf {
	
	// 数据访问数据库的连接对象
	protected static Connection con = null;
	// 预编译你写的sql语句
	protected PreparedStatement ps = null;
	// 查询预编译的sql语句
	protected ResultSet rs = null;
	// 获取数据库链接
	@SuppressWarnings("finally")

	public static void main(String[] args) throws Exception {
			UserDao userdao = new UserDao();
			String url = "dawda";
			int i;
	//		i = userdao.addRecord("jugjd","dawd",url,"1970-01-01 00:00:01");
	//		i = userdao.adduser("dawf","fefwfa","1970-01-01 00:00:01","13241414154","524y5fds","dwadwwdw","fessawd",1);
		//	i = userdao.uploadRecordAlg("juhyresd","23213","e3242");
		//   i = userdao.uploadRecordDoc("juhyresd","grghty","dadwdad","fsefsefs");
		//	i= userdao.deleteRecord("juhyresd");
		//	i = userdao.deleteUser("dawf");
		//	i= userdao.addNews("second", "xingqiyi","dead person", "1970-01-01 00:02:30");
		//	i = userdao.uploadNews("xingqiyi", "dead-person alive");
		//	i = userdao.deleteNews("xingqiyi");
		//	i = userdao.addAns("wda","defsfafaw","12");
		//	String ans = userdao.searchAns("wda");
		//	i = userdao.uploadAns("dwadafw","grhuejf","12");
		//	i = userdao.deleteAns("12");
		//	dataBaseDump("root","scolisis","backup100");
		//	i=userdao.addMessage("12345","54321","hello,nice","1970-01-01 00:00:01");
		//	i =userdao.deleteMessage("OhIxZQkBAu8");
		/*	
		    List<news> n = userdao.selectNews("1970-01-01 00:02:30");
			for (int m = 0; m < n.size(); m++) {
		            news x = n.get(m);   
		            String t =x.getNews_id();                
		            System.out.println(t);
		        }*/
		/*	List<message> mess = userdao.searchMessagebyTime("12345","54321","1969-01-01 00:02:30","1978-01-01 00:00:01");
			for (int m = 0; m < mess.size(); m++) {
	            message x = mess.get(m);   
	            String t = x.getContent();                
	            System.out.println(t);          这里我建表项的时候只建了A发给B 没有B发给A  但跑通了  
	        }*/
		/*	List<message> mess = userdao.searchMessagebyNum("12345","54321",2);
			for (int m = 0; m < mess.size(); m++) {
	            message x = mess.get(m);   
	            String t = x.getContent();                
	            System.out.println(t);          
	        }   */
			List<record> rec = userdao.selectRecord("dawd","1969-01-01 00:02:30","1979-01-01 00:02:30");
			for (int m = 0; m < rec.size(); m++) {
	            record x = rec.get(m);   
	            String t = x.getResult();                
	            System.out.println(t);     
	            t = x.getUrl();
	            System.out.println(t);
	        }   
	}
	
	public static Connection getCon() {
		try {
			// 加载mysql驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 获取数据库链接
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scolisis?useSSL=false","root", "ljx18981974900");
			System.out.println("链接成功");
			return con;
		} catch (Exception e) {
			System.out.println("链接失败" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	public int adduser(String user_id,String name,String birthdate,String phone_number,String email_address,String credential,String user_password,int account_type ) {
  
		int i=0;
		int max=0;
		try {
			// 定义一个sql语句
			// String
			// sql="SELECT a.id as 序号,a.salary as '薪水',b.`name` as '姓名'  from salary a LEFT JOIN `user` b on a.u_id=b.id";
			String sql = "insert into user(user_id,name,birthdate,phone_number,email_address,credential,user_password,account_type) values(?,?,?,?,?,?,?,?)";
			// 获取数据库连接
			con = getCon();
			// 预编译sql语句
			ps = con.prepareStatement(sql);
			ps.setString(1,user_id);
			ps.setString(2, name);
			ps.setString(3,birthdate);
			ps.setString(4, phone_number);
			ps.setString(5, email_address);
			ps.setString(6, credential);
			ps.setString(7, user_password);
			ps.setInt(8, account_type);
		    i=ps.executeUpdate();   
		   
		} catch (Exception e) {
			System.out.println("查询错误" + e.getMessage());
		} finally {
			try {
				
				ps.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
      
		return i;
	}
	
	public int addRecord(String record_id,String user_id,String url,String detect_date) {    //这个匹配我不管，我默认我可以获取id
		  
		int i=0;
		try {
			// 定义一个sql语句
			// String
			// sql="SELECT a.id as 序号,a.salary as '薪水',b.`name` as '姓名'  from salary a LEFT JOIN `user` b on a.u_id=b.id";
			String sql = "insert into x_ray_record(record_id,user_id,url,diagnose_result,detect_date,checked_by_doctor,doctor_id,doctor_result) values(?,?,?,?,?,?,?,?)";
			// 获取数据库连接
			con = getCon();
			Date date=null; 
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			date=formatter.parse(detect_date); 
			//java的date转mysql的datetime
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        //从前端或者自己模拟一个日期格式，转为String即可
	        String dateStr = format.format(date);
			// 预编译sql语句
			ps = con.prepareStatement(sql);
			ps.setString(1, record_id);
			ps.setString(2,user_id);
			ps.setString(3, url);
			ps.setObject(4,null);
			ps.setString(5, dateStr);
			ps.setObject(6,null);
			ps.setObject(7,null);
			ps.setObject(8,null);
		    i=ps.executeUpdate();	
 
		} catch (Exception e) {
			System.out.println("查询错误" + e.getMessage());
		} finally {
			try {
				
				ps.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
      
		return i;
	}	

	public int uploadRecordAlg(String record_id,String diagnose_result,String url) {
		int i=0;
		try {
			// 定义一个sql语句
			// String
			// sql="SELECT a.id as 序号,a.salary as '薪水',b.`name` as '姓名'  from salary a LEFT JOIN `user` b on a.u_id=b.id";
			String sql = "update x_ray_record set diagnose_result='" + diagnose_result + "' ,  url='" + url + "' where record_id='"+ record_id + "'";
			// 获取数据库连接
			con = getCon();
			// 预编译sql语句
			ps = con.prepareStatement(sql);
		    i=ps.executeUpdate();
 
		} catch (Exception e) {
			System.out.println("查询错误" + e.getMessage());
		} finally {
			try {
				
				ps.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return i;
	}  
	
	public int uploadRecordDoc(String record_id,String doctor_id,String url,String doctor_result) {
		int i=0;
		int yes=1;
		try {
			// 定义一个sql语句
			// String
			// sql="SELECT a.id as 序号,a.salary as '薪水',b.`name` as '姓名'  from salary a LEFT JOIN `user` b on a.u_id=b.id";
			String sql = "update x_ray_record set doctor_result='" + doctor_result + "' ,  url='" + url + "' , checked_by_doctor ='"+ yes + "' , doctor_id='" + doctor_id + "' where record_id='"+ record_id + "'";
			// 获取数据库连接
			con = getCon();
			// 预编译sql语句
			ps = con.prepareStatement(sql);
		    i=ps.executeUpdate();
 
		} catch (Exception e) {
			System.out.println("查询错误" + e.getMessage());
		} finally {
			try {
				
				ps.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return i;
	}
	
	public List<record> selectRecord(String user_id,String start_date,String end_date) {    //这个匹配我不管，我默认我可以获取id
		List<record> list = new ArrayList<record>();  
		int i=0;
		try {
			// 定义一个sql语句
			// String
			// sql="SELECT a.id as 序号,a.salary as '薪水',b.`name` as '姓名'  from salary a LEFT JOIN `user` b on a.u_id=b.id";
			//"select * from news where news_time = STR_TO_DATE(('"+time+"'),'%Y-%m-%d %H:%i:%s')";
			String sql = "select diagnose_result,url from x_ray_record where (user_id='"+user_id+"' and detect_date >= STR_TO_DATE(('"+start_date+"'),'%Y-%m-%d %H:%i:%s') and detect_date <= STR_TO_DATE(('"+end_date+"'),'%Y-%m-%d %H:%i:%s'))";
			// 获取数据库连接
			con = getCon();
			// 预编译sql语句
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();	
			while (rs.next()) {
				record rec = new record();
				rec.setResult(rs.getString("diagnose_result"));
				rec.setUrl(rs.getString("url"));
				list.add(rec);
			}	
		} catch (Exception e) {
			System.out.println("查询错误" + e.getMessage());
		} finally {
			try {
				
				ps.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
      
		return list;
	}
	public int deleteRecord(String record_id) {
		int i=0;
		try {
			// 定义一个sql语句
			// String
			// sql="SELECT a.id as 序号,a.salary as '薪水',b.`name` as '姓名'  from salary a LEFT JOIN `user` b on a.u_id=b.id";
			String sql = "delete from x_ray_record where record_id='"+record_id+"'";
			// 获取数据库连接
			con = getCon();
			// 预编译sql语句
			ps = con.prepareStatement(sql);
		    i=ps.executeUpdate();
 
		} catch (Exception e) {
			System.out.println("查询错误" + e.getMessage());
		} finally {
			try {
			
				ps.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return i;
	}
	public int deleteUser(String user_id) {
		int i=0;
		try {
			// 定义一个sql语句
			// String
			// sql="SELECT a.id as 序号,a.salary as '薪水',b.`name` as '姓名'  from salary a LEFT JOIN `user` b on a.u_id=b.id";
			String sql = "delete from user where user_id='"+user_id+"'";
			// 获取数据库连接
			con = getCon();
			// 预编译sql语句
			ps = con.prepareStatement(sql);
		    i=ps.executeUpdate();
 
		} catch (Exception e) {
			System.out.println("查询错误" + e.getMessage());
		} finally {
			try {
			
				ps.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return i;
	}
	public int addNews(String news_id,String news_title, String news_content, String news_time) {
		  
		int i=0;
		try {
			// 定义一个sql语句
			// String
			// sql="SELECT a.id as 序号,a.salary as '薪水',b.`name` as '姓名'  from salary a LEFT JOIN `user` b on a.u_id=b.id";
			String sql = "insert into news(news_id,news_title,news_content,news_time) values(?,?,?,?)";
			// 获取数据库连接
			con = getCon();
			// 预编译sql语句
			ps = con.prepareStatement(sql);
			//string转java的Date
			Date date=null; 
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			date=formatter.parse(news_time); 
			//java的date转mysql的datetime
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        //从前端或者自己模拟一个日期格式，转为String即可
	        String dateStr = format.format(date);
	        ps.setString(1, news_id);
			ps.setString(2, news_title);
			ps.setString(3, news_content);
			ps.setString(4, dateStr);
			
		    i=ps.executeUpdate();
 
		} catch (Exception e) {
			System.out.println("查询错误" + e.getMessage());
		} finally {
			try {
				
				ps.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
      
		return i;
	}
	
	public int uploadNews(String news_title,String news_content) {
		int i=0;
		try {
			// 定义一个sql语句
			// String
			// sql="SELECT a.id as 序号,a.salary as '薪水',b.`name` as '姓名'  from salary a LEFT JOIN `user` b on a.u_id=b.id";
			String sql = "update news set news_content='" + news_content +"' where news_title='" + news_title + "'";
			// 获取数据库连接
			con = getCon();
			// 预编译sql语句
			ps = con.prepareStatement(sql);
		    i=ps.executeUpdate();
 
		} catch (Exception e) {
			System.out.println("查询错误" + e.getMessage());
		} finally {
			try {
				
				ps.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return i;
	}
	public int deleteNews(String news_title) {
		int i=0;
		try {
			// 定义一个sql语句
			// String
			// sql="SELECT a.id as 序号,a.salary as '薪水',b.`name` as '姓名'  from salary a LEFT JOIN `user` b on a.u_id=b.id";
			String sql = "delete from news where news_title='"+news_title+"'";
			// 获取数据库连接
			con = getCon();
			// 预编译sql语句
			ps = con.prepareStatement(sql);
		    i=ps.executeUpdate();
 
		} catch (Exception e) {
			System.out.println("查询错误" + e.getMessage());
		} finally {
			try {
			
				ps.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return i;
	}
	public int addAns(String keyword,String answer,String ans_id) {
		int i=0;
		try {
			String sql = "insert into chatbot(ans_id,keyword,answer) values(?,?,?)";
			// 获取数据库连接
			con = getCon();
			// 预编译sql语句
			ps = con.prepareStatement(sql);
	        ps.setString(1, ans_id);
			ps.setString(2, keyword);
			ps.setString(3, answer);
			i=ps.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("插入答案错误" + e.getMessage());
		}
		finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return i;
	}	
	public String searchAns(String keyword) {
		String ans = null;
		try {
			String sql="select ans_id,answer from chatbot where keyword='"+keyword+"'";
			con=getCon();
			ps = con.prepareStatement(sql);
		    rs=ps.executeQuery();	
		    while(rs.next())
		    ans=rs.getString("answer");
		}
		catch (Exception e) {
			System.out.println("查询答案错误" + e.getMessage());
		}
		finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ans;
	}
	public int uploadAns(String keyword,String answer,String ans_id) {
		int i=0;
		try {
			String sql="update chatbot set keyword='"+keyword+"', answer='"+answer+"' where ans_id='"+ans_id+"'";
			con = getCon();
			ps = con.prepareStatement(sql);
		    i=ps.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("上传错误" + e.getMessage());
		} finally {
			try {

				ps.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return i;
	}	
	public int deleteAns(String ans_id) {
		int i=0;
		try {
			String sql="delete from chatbot where ans_id="+ans_id;
			con = getCon();
			ps = con.prepareStatement(sql);
			i=ps.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("删除错误" + e.getMessage());
		} finally {
			try {

				ps.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return i;
	}
	
	public static String getRandomString(int length){
	    String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    Random random=new Random();
	    StringBuffer sb=new StringBuffer();
	    for(int i=0;i<length;i++){
	      int number=random.nextInt(62);
	      sb.append(str.charAt(number));
	    }
	    return sb.toString();
	}
	
	public int addMessage(String sender_id,String receiver_id,String content,String time)
	{
		int i=0;
		try {
			// 定义一个sql语句
			// String
			// sql="SELECT a.id as 序号,a.salary as '薪水',b.`name` as '姓名'  from salary a LEFT JOIN `user` b on a.u_id=b.id";
			String sql = "insert into message(message_id,sender_id,receiver_id,content,time,unRead) values(?,?,?,?,?,?)";
			// 获取数据库连接
			con = getCon();
			// 预编译sql语句
			//string转java的Date
			Date date=null; 
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			date=formatter.parse(time); 
			//java的date转mysql的datetime
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        //从前端或者自己模拟一个日期格式，转为String即可
	        String dateStr = format.format(date);
	        
			ps = con.prepareStatement(sql);
			ps.setString(1, getRandomString(11));
			ps.setString(2,sender_id);
			ps.setString(3,receiver_id);
			ps.setString(4, content);
			ps.setString(5,dateStr);
			ps.setObject(6,null);
		    i=ps.executeUpdate();

		} catch (Exception e) {
			System.out.println("查询错误" + e.getMessage());
		} finally {
			try {

				ps.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return i;



	};
	
	public int deleteMessage(String message_id) {
		int i=0;
		try {
			// 定义一个sql语句
			// String
			// sql="SELECT a.id as 序号,a.salary as '薪水',b.`name` as '姓名'  from salary a LEFT JOIN `user` b on a.u_id=b.id";
			String sql = "delete from message where message_id='"+message_id+"'";
			// 获取数据库连接
			con = getCon();
			// 预编译sql语句
			ps = con.prepareStatement(sql);
		    i=ps.executeUpdate();

		} catch (Exception e) {
			System.out.println("查询错误" + e.getMessage());
		} finally {
			try {

				ps.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return i;
	}
	
	public List<news> selectNews(String time) 
	{int i;
		List<news> list = new ArrayList<news>();
	try {
		// 定义一个sql语句
		// String
		// sql="SELECT a.id as 序号,a.salary as '薪水',b.`name` as '姓名'  from salary a LEFT JOIN `user` b on a.u_id=b.id";
		String sql = "select * from news where news_time = STR_TO_DATE(('"+time+"'),'%Y-%m-%d %H:%i:%s')";
		// 获取数据库连接
		con = getCon();
		// 预编译sql语句
		ps = con.prepareStatement(sql);
		rs=ps.executeQuery();
		while (rs.next()) {
			news n = new news();
			n.setNews_content(rs.getString("news_content"));
			n.setNews_id(rs.getString("news_id"));
			n.setNews_time(rs.getString("news_time"));
			n.setNews_title(rs.getString("news_title"));
			list.add(n);
		}

	} catch (Exception e) {
		System.out.println("查询错误" + e.getMessage());
	} finally {
		try {
			ps.close();
			con.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	return list;
	};
	public List<message> searchMessagebyTime(String user1_id,String user2_id,String start_date,String end_date) {
		List<message> list = new ArrayList<message>();  
		int i=0;
		try {
			// 定义一个sql语句
			// String
			// sql="SELECT a.id as 序号,a.salary as '薪水',b.`name` as '姓名'  from salary a LEFT JOIN `user` b on a.u_id=b.id";
			String sql = "select content from message where ((sender_id='"+user1_id+"'and receiver_id='"+user2_id+"')or(sender_id='"+user2_id+"'and receiver_id='"+user1_id+"')) and time >= STR_TO_DATE(('"+start_date+"'),('%Y-%m-%d %H:%i:%s')) and time <= STR_TO_DATE(('"+end_date+"'),('%Y-%m-%d %H:%i:%s'))";
			// 获取数据库连接
			con = getCon();
			// 预编译sql语句
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				message mess = new message();
				mess.setContent(rs.getString("content"));
				list.add(mess);
			}
 
		} catch (Exception e) {
			System.out.println("查询错误" + e.getMessage());
		} finally {
			try {
				
				ps.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
      
		return list;
	}
	
	public List<message> searchMessagebyNum(String user1_id,String user2_id,int n) {
		List<message> list = new ArrayList<message>();  
		int i=0;
		try {
			// 定义一个sql语句
			// String
			// sql="SELECT a.id as 序号,a.salary as '薪水',b.`name` as '姓名'  from salary a LEFT JOIN `user` b on a.u_id=b.id";
			//String sql = "select content from message where ((sender_id='"+user1_id+"'and receiver_id='"+user2_id+"')or(sender_id='"+user2_id+"'and receiver_id='"+user1_id+"')) order by UNIX_TIMESTAMP(time) desc";
			String sql = "select content from message where ((sender_id='"+user1_id+"'and receiver_id='"+user2_id+"')or(sender_id='"+user2_id+"'and receiver_id='"+user1_id+"')) order by UNIX_TIMESTAMP(time) desc limit "+n;		
			// 获取数据库连接
			con = getCon();
			// 预编译sql语句
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				message mess = new message();
				mess.setContent(rs.getString("content"));
				list.add(mess);
			}
		    rs=ps.executeQuery();
 
		} catch (Exception e) {
			System.out.println("查询错误" + e.getMessage());
		} finally {
			try {
				
				ps.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
      
		return list;
	}
}









 



