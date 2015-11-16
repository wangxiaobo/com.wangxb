package com.sxt.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.com.jdls.foundation.util.FileUtil;
import cn.com.jdls.foundation.util.StringUtil;
import cn.com.jdls.foundation.util.SysUtil;
import cn.com.servyou.bonde.commons.util.JsonUtil;
import cn.com.servyou.bonde.commons.vo.ResultVO;

import com.sxt.model.ImageInfo;
import com.sxt.model.UserInfo;
import com.sxt.service.IUserService;
import com.xst.utils.CommonUtil;
import com.xst.utils.ConstantsUtil;
import com.xst.utils.QRCodeUtil;
import com.xst.utils.UploadFileInfoGenerateUtil;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    private static final Logger log = Logger.getLogger(UserController.class);

	@Resource
	private IUserService userService;
	@Autowired
	private  HttpServletRequest request;
	


	@RequestMapping(value = "/login")
	@ResponseBody
	public ResultVO login(){
		
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		String hql = "from UserInfo  t where  t.loginName ='"+StringUtil.trim(username)+"'and t.loginPw ='"+pwd+"'";
		Object jiect = userService.getObjectByHql(hql, null);
		if(null == jiect){
			return  ResultVO.setFail("0001", "用户名，密码不正确！");
		}
		else{
			HttpSession session = request.getSession();
			session.setAttribute(ConstantsUtil.LOGIN_USER_INFO, jiect);
			return  ResultVO.setSuccess(jiect);
		}
		
	}

	@RequestMapping(value = "/loginOut")
	@ResponseBody
	public ResultVO loginOut() {
		HttpSession session = request.getSession();
		session.removeAttribute(ConstantsUtil.LOGIN_USER_INFO);
		return ResultVO.setSuccess("注销成功");
	}
	
	@RequestMapping(value = "/userList")
	@ResponseBody
	public List<UserInfo> userList(){
		
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
				
		List<UserInfo> userList = (List<UserInfo>) userService.getAllList(UserInfo.class);
		if(userList.isEmpty()){
			return userList ;
		}
		else{
			return userList;
		}
		
	}
	/*@RequestMapping(value = "/search")
	@ResponseBody
	public List<UserInfo> search(String key){
		String hql = "from UserInfo  t where t.xm like ? or t.sjdh like ? or t.loginName  like ?";
		String keyStr = StringUtil.nvl(key, "");
		String [] paramValues = new String[3];
		paramValues[0] = "%"+keyStr+"%";
		paramValues[1] = "%"+keyStr+"%";
		paramValues[2] = "%"+keyStr+"%";
				
		List userList = userService.getResultsByHql(hql, paramValues);
	    return userList;

		
	}*/
	
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(String key){
		
		//查询条件
		String keyStr = request.getParameter("key");
		//分页
		int pageIndex =Integer.valueOf( request.getParameter("pageIndex"));
		int pageSize = Integer.valueOf( request.getParameter("pageSize"));
		//字段排序
		String sortField = request.getParameter("sortField");
		String sortOrder = request.getParameter("sortOrder");
		
		String hql = "from UserInfo  t where t.xm like ? or t.sjdh like ? or t.loginName  like ?  order by "+sortField+"  "+sortOrder;
		
		keyStr = StringUtil.nvl(keyStr, "");
		String [] paramValues = new String[3];
		paramValues[0] = "%"+keyStr+"%";
		paramValues[1] = "%"+keyStr+"%";
		paramValues[2] = "%"+keyStr+"%";
				
		List userList = userService.getResultsByHql(hql, paramValues);
		
		Map<String, Object> result= new HashMap<String, Object>();
		int total = userList.size();
		result.put("total", total);
		int start = pageIndex*pageSize> total?total:pageIndex*pageSize;
		int end =(pageIndex+1)*pageSize > total? total:(pageIndex+1)*pageSize;
		result.put("data", userList.subList(start, end));
		
	    return result;

		
	}
	/****上传文件***/
	@RequestMapping(value="/uploadFile_{userId}")
	public void uploadFile(@RequestParam(value = "file", required = false) MultipartFile file,@PathVariable("userId") Integer userId,HttpServletResponse  response) throws Exception{
		String filePath = null;
        ServletContext  servlet = request.getSession().getServletContext();
        response.setContentType("text/xml");
		//获取当前操作用户
		if(null != file){
			String fileName = file.getOriginalFilename();
			String model = "public";
		    filePath = UploadFileInfoGenerateUtil.getUploadFilePath(model, UploadFileInfoGenerateUtil.getSuffix(fileName));
		    FileUtil.writeBytesToFile(filePath, file.getBytes()) ; //保存文件
		    
		    ImageInfo  imageInfo = new ImageInfo();
		 	imageInfo.setPath(filePath);
		 	
			if(null != userId && 0!=userId){
				imageInfo.setUserId(userId);
				imageInfo.setFlag("0"); //只是上传了
			}
			imageInfo.setImageId(CommonUtil.createUUID());
			userService.addObject(imageInfo);
			imageInfo.setFlag("3"); //默认公共上穿
			
		    log.info(imageInfo);
		    
		    response.getWriter().write(JsonUtil.getJsonStringForJavaPOJO(ResultVO.setSuccess(imageInfo)));
			
		}else{
		    response.getWriter().write(JsonUtil.getJsonStringForJavaPOJO(ResultVO.setFail("0001")));
		}

	}
	
	@RequestMapping(value="/file/{imageId}")
	public void viewFile( @PathVariable("imageId") String imageId,HttpServletResponse  response) throws Exception {
		
		response.setContentType("image/jpeg;charset=UTF-8");
		ServletOutputStream out = response.getOutputStream();
		String[] params=imageId.split("-");
		//获取图片对象
        ImageInfo image= (ImageInfo) userService.get(ImageInfo.class,params[0]);

		if(null == image){
			out.write(0);
		}else{
			
			/*if( params.length>1){
				float quality=Float.valueOf(params[1])/10.0f;
				Thumbnails.of(image.getPath()).scale(quality).toOutputStream(out);
			}else{
				out.write(FileUtil.readFileToBytes(image.getPath()));
			}*/
			out.write(FileUtil.readFileToBytes(image.getPath()));
		}
		out.flush();
		out.close();
	}


	@RequestMapping(value = "/saveUser")
	@ResponseBody
	public ResultVO saveUser(UserInfo userInfo){
		
		if(null == userInfo){
			return  ResultVO.setFail("0001");
		}
		UserInfo user =  (UserInfo) userService.get(UserInfo.class, userInfo.getUserId());
		
		//设置页面为传入属性
		/*userInfo.setLoginName(userInfo.getLoginName());
		userInfo.setLoginPw(userInfo.getLoginPw());*/

		userService.saveOrUpdate(userInfo);
		return  ResultVO.setSuccess(userInfo);
			
	}
	
	@RequestMapping(value = "/addUser")
	@ResponseBody
	public ResultVO addUser(UserInfo userInfo){
		
		if(null == userInfo){
			return  ResultVO.setFail("用户信息为空");
		}
		List userList =  userService.getList(userInfo);
		if(!userList.isEmpty()){
			return  ResultVO.setFail("该用户已存在");
		}
		//获取最大ID 
		userInfo.setUserId(getMaxUserId()+1);
		userService.addObject(userInfo);
		return  ResultVO.setSuccess(userInfo);
			
	}
	
	@RequestMapping(value = "/saveImage")
	@ResponseBody
	public ResultVO saveImage(String imageStr,String delImageStr){
	   List<ImageInfo> imageList= JsonUtil.getListForJson(imageStr, ImageInfo.class);
	   List<ImageInfo> delImageList= JsonUtil.getListForJson(delImageStr, ImageInfo.class);
	   for(ImageInfo delInfo:delImageList){
		   //删除文件
		   FileUtil.deleteFile(delInfo.getPath());
		   //删除记录
		   userService.deleteObject(ImageInfo.class, delInfo.getImageId());
		   
	   }
	   //修改
	   for(ImageInfo info:imageList){
		   if(info.getPath().indexOf("public") != -1){
			   //拷贝文件路劲
			   String  filePath = UploadFileInfoGenerateUtil.getUploadFilePath("private", UploadFileInfoGenerateUtil.getSuffix(info.getPath()));
			   FileUtil.copyFileToFile(info.getPath(), filePath);
			   //删除文件
			   FileUtil.deleteFile(info.getPath());
			   info.setPath(filePath);

		   }
		   
		   if(StringUtil.isNullString(info.getLrsj())){ 
			   info.setLrsj(SysUtil.getSysDate());
		   }

		   userService.updateObject(info);
	   }
	   return  ResultVO.setSuccess(imageList);
			
	}
	
	@RequestMapping(value = "/imageList")
	@ResponseBody
	public ResultVO imageList( ImageInfo imageInfo){
		
		StringBuffer 	hql = new StringBuffer();
		 hql.append("select new ImageInfo(info,user.xm )from ImageInfo info , UserInfo user where info.userId = user.userId"); 
		 hql.append("  and info.flag='"+imageInfo.getFlag()+"'");
	         if(null != imageInfo.getUserId()){
	        	 hql.append(" and info.userId="+imageInfo.getUserId());
	         }
		 hql.append(" order by info.lrsj desc");
		
		List<Object> imgList =  userService.getResultsByHql(hql.toString(), null);
		

		return  ResultVO.setSuccess(imgList);
	
	}
	
	@RequestMapping(value="/QRCode/{userId}")
	public void QRCode( @PathVariable("userId") Integer userId,HttpServletResponse  response) throws Exception {
		
		response.setContentType("image/jpeg;charset=UTF-8");
		ServletOutputStream out = response.getOutputStream();
		//获取图片对象
		UserInfo user = (UserInfo) userService.get(UserInfo.class,userId);

		if(null == user){
			out.write(0);
		}else{
			
			StringBuffer content = new StringBuffer();
			
			content.append("BEGIN:VCARD\n");
			content.append("VERSION:3.0\n");
			content.append("N:"+user.getXm()+" \n");
			content.append("EMAIL:"+user.getEmail()+"\n");
			content.append("TEL:"+user.getSjdh()+"\n");
			content.append("CELL:"+user.getSjdh()+"\n");
			content.append("ADR:"+user.getJzd()+"\n");
			content.append("ORG:"+user.getJg()+"\n");
			content.append("TITLE:"+user.getHxDm()+"\n");
			content.append("URL:\n");
			content.append("NOTE:"+user.getBz1()+"\n");
			content.append("END:VCARD");
			
			ByteArrayOutputStream byteOut=QRCodeUtil.encodeByte(content.toString(),200,200);
			
			out.write(byteOut.toByteArray());
		}
		out.flush();
		out.close();
	}
	
	@RequestMapping(value="/doc")
	public void doc( HttpServletResponse  response) throws Exception {
        response.setContentType("application/msword;charset=UTF-8");  
		response.addHeader("Content-Disposition","filename="+"jjh.doc"); 		ServletOutputStream out = response.getOutputStream();		
		InputStream in = new FileInputStream(request.getSession().getServletContext().getRealPath("/")+"/doc/三十年广州聚会集结号.doc"); 
				//获取图片对象
		out.write(FileUtil.readFileToBytes(request.getSession().getServletContext().getRealPath("/")+"/doc/三十年广州聚会集结号.doc"));
		 //写文件  
        int b;  
        while((b=in.read())!= -1)  
        {  
            out.write(b);  
        } 
		out.flush();
		in.close();
		out.close();
		
	}
	
	
	private  Integer getMaxUserId(){		
		Integer maxUserid = (Integer) userService.getObjectBySql("select max(t.user_id) from user_info t ", null);
		return maxUserid;
	}
}
