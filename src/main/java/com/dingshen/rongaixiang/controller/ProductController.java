package com.dingshen.rongaixiang.controller;

import com.dingshen.rongaixiang.domain.Image;
import com.dingshen.rongaixiang.domain.ImgResult;
import com.dingshen.rongaixiang.domain.Product;
import com.dingshen.rongaixiang.service.ImageService;
import com.dingshen.rongaixiang.service.ProductService;
import com.github.pagehelper.Page;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ImageService imageService;
    @ResponseBody
    @RequestMapping("/findAllProductType")
    public List<String> findAllProductType(){
        List<String> stringList=productService.findAllProductType();
        return stringList;
    }
    @RequestMapping("/singleProduct{id}")
    @ResponseBody
    public ModelAndView singleProduct(Integer id,ModelAndView modelAndView){
        Image singleImage=imageService.selectByPrimaryKey(id);
        String productName=productService.findProductName(String.valueOf(id));
        modelAndView.addObject("productName",productName);
        modelAndView.addObject("singleImage",singleImage);
        modelAndView.setViewName("portfolio-single");
        return modelAndView;
    }
    @ResponseBody
    @RequestMapping("/productList")
    public Map productlist(Integer page, Integer limit, String type, String keyword) {
        Page<Map> userInfos= productService.findAllProduct (page,limit,type,keyword );
        Map result=new HashMap();
        result.put("code" , 0);
        result.put("msg" , "");
        result.put("count" , userInfos.getTotal ());
        result.put("data" , userInfos.getResult ());
        return result;
    }
    @RequestMapping("/productSelectByPrimaryKey")
    public Product selectByPrimaryKey(Integer uid){
        return productService.selectByPrimaryKey(uid);
    }


    /**
     * 文件上传
     * @param file
     * @param request
     * @return
     */

    @SuppressWarnings("deprecation")
@RequestMapping("/upload")
@ResponseBody
public ImgResult uplpad(MultipartFile file, HttpServletRequest request) {
    String desFilePath = "";
    String oriName = "";
    ImgResult result = new ImgResult();
    Map<String, String> dataMap = new HashMap<>();
    ImgResult imgResult = new ImgResult();
    try {
        // 1.获取原文件名
        oriName = file.getOriginalFilename();
        // 2.获取原文件图片后缀，以最后的.作为截取(.jpg)
        String extName = oriName.substring(oriName.lastIndexOf("."));
        // 3.生成自定义的新文件名，这里以UUID.jpg|png|xxx作为格式（可选操作，也可以不自定义新文件名）
        String uuid = UUID.randomUUID().toString();
        String newName = uuid + extName;
        // 4.获取要保存的路径文件夹
        String filePath = "E:\\rongaixiang\\src\\main\\resources\\static\\upload";
        //String filePath = request.getSession().getServletContext().getRealPath("upload/");
        // 5.保存图片
        desFilePath = filePath + "\\" + newName;
        File desFile = new File(desFilePath);
        file.transferTo(desFile);
        // 6.返回保存结果信息
        result.setCode(0);
        dataMap = new HashMap<>();
        dataMap.put("fileName", newName);
        result.setData(dataMap);
        result.setMsg(oriName + "上传成功！");
        return result;
    } catch (IllegalStateException e) {
        imgResult.setCode(1);
        System.out.println(desFilePath + "图片保存失败");
        return imgResult;
    } catch (IOException e) {
        imgResult.setCode(1);
        System.out.println(desFilePath + "图片保存失败--IO异常");
        return imgResult;
    }
}

    @RequestMapping("/productAdd")
    @ResponseBody
    public String productAdd(Product product,String imgUrls) throws IOException {
        //单个文件上传
//        if(upfile.getSize()>0){
//            String filename =System.currentTimeMillis()+upfile.getOriginalFilename();
//            String path = "E:\\rongaixiang\\src\\main\\resources\\static\\upload" +filename;
//            //String path = req.getServletContext().getRealPath("/") + "upload\\" + filename;
//            File file = new File(path);
//            upfile.transferTo(file);
//            product.setFileName(filename);
//        }
        //存入图片信息
        //保存图片信息同时将图片id保存到产品中
        if (!imgUrls.equals("")&&imgUrls!=null) {
            String[] urls = imgUrls.split(",");
            StringBuffer imgName = new StringBuffer();
            Image image = new Image();
            for (int i = 0; i < urls.length; i++) {
                image.setImgName(urls[i]);
                image.setImgInfomation(product.getInformation());
                image.setImgType(product.getProductType());
                imageService.insert(image);
                Integer imageId = imageService.findIdByName(urls[i]);
                imgName.append(imageId + ",");
            }
            product.setFileName(imgName.toString());
        }
        product.setCreateTime(new Date());
        productService.insert(product);
        return "1";
    }
    /**
     * 判断文件夹是否存在
     * @param file
     */
    public static void mkDir(File file) {
        if (file.getParentFile().exists()) {
            file.mkdir();
        } else {
            mkDir(file.getParentFile());
            file.mkdir();
        }
    }
    @RequestMapping("/showImage{filename}")
    public void showImage(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception{
        //String filePath="D:\\worktest1\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\springmvcdemo1\\upload\\122.png" ;
        //String path = request.getServletContext().getRealPath("/upload/");
        String path = "E:\\rongaixiang\\src\\main\\resources\\static\\upload";
        File file = new File(path + File.separator + filename);
        //File file = new File(filepath);
        FileInputStream fis = null;
        response.setContentType("image/gif");
        OutputStream out = response.getOutputStream();
        fis = new FileInputStream(file);
        byte[] b = new byte[fis.available()];
        fis.read(b);
        out.write(b);
        out.flush();
        fis.close();
    }
    @RequestMapping("/productUpdate")
    @ResponseBody
    public String productUpdate(Product product,String imgUrls){
        if (!imgUrls.equals("")&&imgUrls!=null) {
            String[] urls = imgUrls.split(",");
            Image image = new Image();
            StringBuffer imgName=new StringBuffer();
            for (int i = 0; i < urls.length; i++) {
                image.setImgName(urls[i]);
                imageService.insert(image);
                Integer imageId = imageService.findIdByName(urls[i]);
                imgName.append(imageId + ",");
            }
            String str=product.getFileName();
            if (str==null){
                product.setFileName(imgName.toString());
                productService.updateByPrimaryKey(product);
            }else {
                String[] strs = str.split(",");
                Integer[] aftIdArray = (Integer[])ConvertUtils.convert(strs, Integer.class);
                for (int j=0;j<aftIdArray.length;j++){
                    imageService.deleteByPrimaryKey(aftIdArray[j]);
                }
                product.setFileName(imgName.toString());
            }
        }
      productService.updateByPrimaryKey(product);
     return "1";
    }
    @RequestMapping("/productDeleteById")
    @ResponseBody
    public Map deleteProductById(Integer uid){
        productService.deleteByPrimaryKey(uid);
        Map result = new HashMap();
        result.put("code" , 0);//0代表成功 其他代表失败
        result.put("msg" , uid + "内容已被删除"); //消息内容
        return result;
    }
    //跳转
    @RequestMapping("/toProductList")
    public ModelAndView toProductList(ModelAndView modelAndView){
        modelAndView.setViewName("product/list");
        return modelAndView;
    }
    @RequestMapping("/toProductAdd")
    public ModelAndView toProductAdd(ModelAndView modelAndView){
        modelAndView.setViewName("product/add");
        return modelAndView;
    }
    @RequestMapping("/toProductUpdate")
    public ModelAndView toProductUpdate(ModelAndView model, Integer uid){
        Product updateProduct=productService.selectByPrimaryKey(uid);
        if (updateProduct.getFileName()!=null&&!updateProduct.getFileName().equals("")) {
            String[] urls = updateProduct.getFileName().split(",");
            //将String数组转换为Integer数组
            Integer[] integers = (Integer[]) ConvertUtils.convert(urls, Integer.class);
            StringBuffer sbf = new StringBuffer();
            for (int i = 0; i < integers.length; i++) {
                Image image = imageService.selectByPrimaryKey(integers[i]);
                sbf.append(image.getImgName() + ",");
            }
            String[] imageName = sbf.toString().split(",");
            model.addObject("imageName", imageName);
        }
        model.addObject ("updateProduct",updateProduct);
        model.setViewName("product/update");
        return model;
    }
    @RequestMapping("/toProductShow")
    public String toProductShow(Model model, Integer uid){
        Product showProduct=productService.selectByPrimaryKey(uid);
        if (showProduct.getFileName()!=null&&!showProduct.getFileName().equals("")){
        String[] urls = showProduct.getFileName().split(",");
        //将String数组转换为Integer数组
        Integer[] integers = (Integer[]) ConvertUtils.convert(urls, Integer.class);
        StringBuffer sbf=new StringBuffer();
        for (int i=0;i<integers.length;i++){
            Image image=imageService.selectByPrimaryKey(integers[i]);
            sbf.append(image.getImgName()+",");
        }
        String[] imageName = sbf.toString().split(",");
        model.addAttribute("imageName",imageName);
        }
        model.addAttribute ("showProduct",showProduct);
        return "product/show";
    }

}
