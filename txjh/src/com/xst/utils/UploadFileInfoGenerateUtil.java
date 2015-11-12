package com.xst.utils;


import cn.com.jdls.foundation.util.FileUtil;
import cn.com.jdls.foundation.util.SysUtil;

/**
 * <p>
 * 标题: 河北网上办税服务厅
 * </p>
 * <p>
 * 功能描述：图片信息生成工具类
 * </p>
 * <p>
 * 版权: 税友软件集团股份有限公司
 * </p>
 * <p>
 * 创建时间：2014-10-23
 * </p>
 * <p>
 * 作者：刘信
 * </p>
 * <p>
 * 修改历史记录：
 * </p>
 *
 */
public class UploadFileInfoGenerateUtil {
    /**
     * 默认后缀名
     */
    private static final String DEFAULT_FILE_SUFFIX = "jpg";
    
    /**
     * 后缀名分隔符
     */
    private static final String FILE_SUFFIX_SPLIT_CHAR = ".";

    /***获取系统上传路径******/
    public static String getUploadFilePath(String nsrsbh,String suffix) {
    	return getUploadFilePath(nsrsbh)+suffix;
    }
    
    public static String getUploadFilePath(String nsrsbh){
        String filePath ="upload/"+nsrsbh+"/"+SysUtil.getSysDateYYYYMMDD()+ "/";
        FileUtil.mkDirs2(filePath);
        filePath = filePath +CommonUtil.createUUID();
        return filePath;
    }
    
    /***获取系统上传路径******/
    public static String getSuffix(String filename) {
    	String []  sts = filename.split("\\.");
        return getFileSuffix( sts[sts.length-1]) ;
    }
   private static String getFileSuffix(String fileSuffix) {
        
        if (null == fileSuffix || fileSuffix.trim().length() == 0 ) {
            fileSuffix = DEFAULT_FILE_SUFFIX;
        } else {
            fileSuffix = fileSuffix.trim();
            if (!fileSuffix.startsWith(FILE_SUFFIX_SPLIT_CHAR)) {
                fileSuffix = FILE_SUFFIX_SPLIT_CHAR + fileSuffix;
            }
        }
        return fileSuffix;
    }
    
}
