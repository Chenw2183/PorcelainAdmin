package com.cwz.ssmspringboot2.controller;


import com.cwz.ssmspringboot2.domain.User;
import com.cwz.ssmspringboot2.domain.User2;
import com.cwz.ssmspringboot2.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Repository、@Service、@Controller 和 @Component 将类标识为Bean
 * @ResponseBody ＋ @Controller合在一起的作用
 * @RestController
 */
@Controller
public class UserController {

    /**
     * 注入
     */
    @Autowired
    private UserService userService;


    List<String[][]> ListArr = new ArrayList<>();

    /**
     * 查询
     * 判断 是否为空 格式输入是否正确
     *
     * @return
     */
    @RequestMapping("SelectAll")
    public String SelectAll(
            HttpServletResponse response,
            Model model,
            @ModelAttribute("date") String date,
            @ModelAttribute("beforedate") String beforedate,
            @ModelAttribute("day") String day,
            @ModelAttribute("WX_004_0019") String WX_004_0019,
            @ModelAttribute("WX_004_1010") String WX_004_1010,
            @ModelAttribute("WX_004_0020") String WX_004_0020,
            @ModelAttribute("WX_004_1011") String WX_004_1011,
            @ModelAttribute("WX_004_1012") String WX_004_1012,
            @ModelAttribute("btn") String btn

    ) {

        //多选框获取数据
        List LWX004 = checkboxs(WX_004_0019, WX_004_1010, WX_004_0020, WX_004_1011, WX_004_1012);


        model.addAttribute("user2_0019", null);
        model.addAttribute("user2_1010", null);
        model.addAttribute("user2_0020", null);
        model.addAttribute("user2_1011", null);
        model.addAttribute("user2_1012", null);


        try {

            if (btn.equals("全部导出")) {
                ArrWriteExcel(Arr2(ListArr), response);
                System.out.println("11111111111111111");
            } else {
                ListArr.clear();
                for (int i = 0; i < LWX004.size(); i++) {
                    /**
                     * if           判断输入框是否为空 如果为空默认为当前时间
                     * else if      判断日期格式是否正确 如果正确开始查询
                     * else         日期格式输入错误！
                     */
                    if (date == null || "".equals(date)) {


                        //获取当前的日期
                        Date d = new Date();

                        //设置日期格式
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        //获取String类型的时间
                        String sdate = df.format(d);
                        model.addAttribute("dateuser", sdate);

                        int cdate = isdaynull(day);
                        //7天后日期（字符串）
                        String out = userdate(sdate, cdate);
                        //返回城市ID的数组
                        int CId[] = CITY_ID59();
                        //返回7天所有城市的指标 没有数据默认 '-' 代替
//            String WX_004_0019arr[][] = new String[CId.length][7];
                        //返回指定的时间段内的有所日期 7天
                        String coldateArr[] = dateSplit(sdate, out);
                        //返回日期格式是否正确
                        String dateTF = isnull(sdate);
                        //全省的数据总和
//          #####################
                        List<User2> user2 = ProvinceSelect(LWX004.get(i) + "", sdate, out);
                        //查询到的7天数据
//            List<User> all = userService.SelectAll(sdate);


                        //判断日期输入的格式是否正确
                        model.addAttribute("dateTF", dateTF);
                        //查询到的7天数据
//            model.addAttribute("all", all);
                        //页面显示的当前时间
                        model.addAttribute("nowdate", sdate);
                        //表格第一栏7天所有日期
                        model.addAttribute("coldateArr", coldateArr);
                        //过滤用户输入的对比天数
                        int contrastday = isdaynull(day);
                        String WX_004_0019arr[][] = new String[CId.length][contrastday + 1];
                        List<User> all = userService.SelectAll(sdate, contrastday);
                        String arr1[][] = transformationArr(dataarrangement(LWX004.get(i) + "", contrastday, CId, all, initializationArr(WX_004_0019arr, contrastday), coldateArr), CId, contrastday);

                        int contrastdayarr[] = new int[contrastday + 1];
                        model.addAttribute("contrastday", contrastdayarr);
                        //城市（中文）
                        model.addAttribute("CITY_ID", CITY_ID());
                        String arr2[][] = BeforeDate(LWX004.get(i) + "", beforedate, contrastday, sdate);
                        String ResultsArr1[] = ResultsArr1(ProvinceArr(user2, coldateArr, isdaynull(day) + 1), CompareprovinceArr(LWX004.get(i) + "", beforedate, contrastday, sdate, day));


//                //全省的数据总和
//                model.addAttribute("user2", ProvinceArr(user2,coldateArr,isdaynull(day)+1));
//                model.addAttribute("WX_004_0019arr",arr1);
//                //二维数组 是返回晚年表0019数据
//                String arr2[][]=BeforeDate(LWX004.get(i)+"",beforedate,contrastday,sdate);
//                model.addAttribute("BeforeDate", arr2);
//                //一维数组 返回晚年表格上的日期
//                model.addAttribute("Comparedatecolumn", Comparedatecolumn(beforedate,contrastday,sdate));
//
//                //一维数组 返回晚年表格上的全省汇总
//                model.addAttribute("CompareprovinceArr", CompareprovinceArr(LWX004.get(i)+"",beforedate,contrastday,sdate,day));
//                //对比计算结果二维数组
//                model.addAttribute("ResultsArr",ResultsArr2(arr1,arr2));
//
//                //对比计算结果全省一维数组
//                String ResultsArr1[]= ResultsArr1(ProvinceArr(user2,coldateArr,isdaynull(day)+1),CompareprovinceArr(LWX004.get(i)+"",beforedate,contrastday,sdate,day));
//                model.addAttribute("ResultsArr1",ResultsArr1);
//                model.addAttribute("Test",null);


                        if (LWX004.get(i).equals("WX_004_0019")) {
                            System.out.println(":WX_004_0019");
                            model.addAttribute("user2_0019", StrArr(ProvinceArr(user2, coldateArr, isdaynull(day) + 1)));
                            model.addAttribute("WX_004_0019arr_0019", arr1);
                            model.addAttribute("BeforeDate_0019", arr2);
                            model.addAttribute("Comparedatecolumn_0019", Comparedatecolumn(beforedate, contrastday, sdate));
                            model.addAttribute("CompareprovinceArr_0019", StrArr(CompareprovinceArr(LWX004.get(i) + "", beforedate, contrastday, sdate, day)));
                            model.addAttribute("ResultsArr_0019", ResultsArr2(arr1, arr2));
                            model.addAttribute("ResultsArr1_0019", ResultsArr1);


                            String Arr2[][] = StrArr2("WX_004_0019", contrastday, coldateArr, Comparedatecolumn(beforedate, contrastday, sdate), arr1, arr2, ResultsArr2(arr1, arr2), StrArr(ProvinceArr(user2, coldateArr, isdaynull(day) + 1)), StrArr(CompareprovinceArr(LWX004.get(i) + "", beforedate, contrastday, sdate, day)), ResultsArr1);

                            ListArr.add(Arr2);

                        }
                        if (LWX004.get(i).equals("WX_004_1010")) {
                            System.out.println(":WX_004_1010");
                            model.addAttribute("user2_1010", StrArr(ProvinceArr(user2, coldateArr, isdaynull(day) + 1)));
                            model.addAttribute("WX_004_0019arr_1010", arr1);
                            model.addAttribute("BeforeDate_1010", arr2);
                            model.addAttribute("Comparedatecolumn_1010", Comparedatecolumn(beforedate, contrastday, sdate));
                            model.addAttribute("CompareprovinceArr_1010", StrArr(CompareprovinceArr(LWX004.get(i) + "", beforedate, contrastday, sdate, day)));
                            model.addAttribute("ResultsArr_1010", ResultsArr2(arr1, arr2));
                            model.addAttribute("ResultsArr1_1010", ResultsArr1);

                            String Arr2[][] = StrArr2("WX_004_1010", contrastday, coldateArr, Comparedatecolumn(beforedate, contrastday, sdate), arr1, arr2, ResultsArr2(arr1, arr2), StrArr(ProvinceArr(user2, coldateArr, isdaynull(day) + 1)), StrArr(CompareprovinceArr(LWX004.get(i) + "", beforedate, contrastday, sdate, day)), ResultsArr1);

                            ListArr.add(Arr2);
                        }
                        if (LWX004.get(i).equals("WX_004_0020")) {
                            System.out.println(":WX_004_0020");
                            model.addAttribute("user2_0020", StrArr(ProvinceArr(user2, coldateArr, isdaynull(day) + 1)));
                            model.addAttribute("WX_004_0019arr_0020", arr1);
                            model.addAttribute("BeforeDate_0020", arr2);
                            model.addAttribute("Comparedatecolumn_0020", Comparedatecolumn(beforedate, contrastday, sdate));
                            model.addAttribute("CompareprovinceArr_0020", StrArr(CompareprovinceArr(LWX004.get(i) + "", beforedate, contrastday, sdate, day)));
                            model.addAttribute("ResultsArr_0020", ResultsArr2(arr1, arr2));
                            model.addAttribute("ResultsArr1_0020", ResultsArr1);

                            String Arr2[][] = StrArr2("WX_004_0020", contrastday, coldateArr, Comparedatecolumn(beforedate, contrastday, sdate), arr1, arr2, ResultsArr2(arr1, arr2), StrArr(ProvinceArr(user2, coldateArr, isdaynull(day) + 1)), StrArr(CompareprovinceArr(LWX004.get(i) + "", beforedate, contrastday, sdate, day)), ResultsArr1);

                            ListArr.add(Arr2);
                        }
                        if (LWX004.get(i).equals("WX_004_1011")) {
                            System.out.println(":WX_004_1011");
                            model.addAttribute("user2_1011", StrArr(ProvinceArr(user2, coldateArr, isdaynull(day) + 1)));
                            model.addAttribute("WX_004_0019arr_1011", arr1);
                            model.addAttribute("BeforeDate_1011", arr2);
                            model.addAttribute("Comparedatecolumn_1011", Comparedatecolumn(beforedate, contrastday, sdate));
                            model.addAttribute("CompareprovinceArr_1011", StrArr(CompareprovinceArr(LWX004.get(i) + "", beforedate, contrastday, sdate, day)));
                            model.addAttribute("ResultsArr_1011", ResultsArr2(arr1, arr2));
                            model.addAttribute("ResultsArr1_1011", ResultsArr1);

                            String Arr2[][] = StrArr2("WX_004_1011", contrastday, coldateArr, Comparedatecolumn(beforedate, contrastday, sdate), arr1, arr2, ResultsArr2(arr1, arr2), StrArr(ProvinceArr(user2, coldateArr, isdaynull(day) + 1)), StrArr(CompareprovinceArr(LWX004.get(i) + "", beforedate, contrastday, sdate, day)), ResultsArr1);

                            ListArr.add(Arr2);
                        }
                        if (LWX004.get(i).equals("WX_004_1012")) {
                            System.out.println(":WX_004_1012");
                            model.addAttribute("user2_1012", StrArr(ProvinceArr(user2, coldateArr, isdaynull(day) + 1)));
                            model.addAttribute("WX_004_0019arr_1012", arr1);
                            model.addAttribute("BeforeDate_1012", arr2);
                            model.addAttribute("Comparedatecolumn_1012", Comparedatecolumn(beforedate, contrastday, sdate));
                            model.addAttribute("CompareprovinceArr_1012", StrArr(CompareprovinceArr(LWX004.get(i) + "", beforedate, contrastday, sdate, day)));
                            model.addAttribute("ResultsArr_1012", ResultsArr2(arr1, arr2));
                            model.addAttribute("ResultsArr1_1012", ResultsArr1);

                            String Arr2[][] = StrArr2("WX_004_1012", contrastday, coldateArr, Comparedatecolumn(beforedate, contrastday, sdate), arr1, arr2, ResultsArr2(arr1, arr2), StrArr(ProvinceArr(user2, coldateArr, isdaynull(day) + 1)), StrArr(CompareprovinceArr(LWX004.get(i) + "", beforedate, contrastday, sdate, day)), ResultsArr1);

                            ListArr.add(Arr2);
                        }


                    } else if ("true".equals(isnull(date))) {

                        //用户输入的日期
                        String sdate = date;
                        model.addAttribute("dateuser", sdate);

                        int cdate = isdaynull(day);
                        //几天后日期（字符串）
                        String out = userdate(sdate, cdate);
                        //返回城市ID的数组
                        int CId[] = CITY_ID59();
                        //返回7天所有城市的指标 没有数据默认 '-' 代替
//            String WX_004_0019arr[][] = new String[CId.length][7];
                        //返回指定的时间段内的有所日期 7天
                        String coldateArr[] = dateSplit(date, out);
                        //判断日期输入的格式是否正确
                        String dateTF = isnull(date);
                        //全省的数据总和
                        List<User2> user2 = ProvinceSelect(LWX004.get(i) + "", date, out);
                        //查询到的7天数据
//            List<User> all = userService.SelectAll(date);
                        //判断日期输入的格式是否正确
                        model.addAttribute("dateTF", dateTF);
//查询到的7天数据
//            model.addAttribute("all", all);
                        //页面显示的当前时间
                        model.addAttribute("nowdate", sdate);
                        //表格第一栏7天所有日期
                        model.addAttribute("coldateArr", coldateArr);

                        int contrastday = isdaynull(day);
                        String WX_004_0019arr[][] = new String[CId.length][contrastday + 1];
                        List<User> all = userService.SelectAll(date, contrastday);
                        String arr1[][] = transformationArr(dataarrangement(LWX004.get(i) + "", contrastday, CId, all, initializationArr(WX_004_0019arr, contrastday), coldateArr), CId, contrastday);
                        //空数组 遍历用户输入的对比天数 初始化表格
                        int contrastdayarr[] = new int[contrastday + 1];
                        model.addAttribute("contrastday", contrastdayarr);
                        //城市（中文）
                        model.addAttribute("CITY_ID", CITY_ID());
                        String arr2[][] = BeforeDate(LWX004.get(i) + "", beforedate, contrastday, sdate);


//                //全省的数据总和
//                model.addAttribute("user2", ProvinceArr(user2,coldateArr,isdaynull(day)+1));
//                model.addAttribute("WX_004_0019arr",arr1);
//                //二维数组 是返回晚年表0019数据
//                model.addAttribute("BeforeDate",arr2);
//                //一维数组 返回晚年表格上的日期
//                model.addAttribute("Comparedatecolumn", Comparedatecolumn(beforedate,contrastday,sdate));
//                //一维数组 返回晚年表格上的全省汇总
//                model.addAttribute("CompareprovinceArr", CompareprovinceArr(LWX004.get(i)+"",beforedate,contrastday,sdate,day));
//                //对比计算结果二维数组
//                model.addAttribute("ResultsArr",ResultsArr2(arr1,arr2));
//                //对比计算结果全省一维数组
                        String ResultsArr1[] = ResultsArr1(ProvinceArr(user2, coldateArr, isdaynull(day) + 1), CompareprovinceArr(LWX004.get(i) + "", beforedate, contrastday, sdate, day));
//                model.addAttribute("ResultsArr1",ResultsArr1);
//
//                model.addAttribute("Test",null);

                        if (LWX004.get(i).equals("WX_004_0019")) {
                            System.out.println(":WX_004_0019");
                            model.addAttribute("user2_0019", StrArr(ProvinceArr(user2, coldateArr, isdaynull(day) + 1)));
                            model.addAttribute("WX_004_0019arr_0019", arr1);
                            model.addAttribute("BeforeDate_0019", arr2);
                            model.addAttribute("Comparedatecolumn_0019", Comparedatecolumn(beforedate, contrastday, sdate));
                            model.addAttribute("CompareprovinceArr_0019", StrArr(CompareprovinceArr(LWX004.get(i) + "", beforedate, contrastday, sdate, day)));
                            model.addAttribute("ResultsArr_0019", ResultsArr2(arr1, arr2));
                            model.addAttribute("ResultsArr1_0019", ResultsArr1);

                            String Arr2[][] = StrArr2("WX_004_0019", contrastday, coldateArr, Comparedatecolumn(beforedate, contrastday, sdate), arr1, arr2, ResultsArr2(arr1, arr2), StrArr(ProvinceArr(user2, coldateArr, isdaynull(day) + 1)), StrArr(CompareprovinceArr(LWX004.get(i) + "", beforedate, contrastday, sdate, day)), ResultsArr1);

                            ListArr.add(Arr2);
                        }
                        if (LWX004.get(i).equals("WX_004_1010")) {
                            System.out.println(":WX_004_1010");
                            model.addAttribute("user2_1010", StrArr(ProvinceArr(user2, coldateArr, isdaynull(day) + 1)));
                            model.addAttribute("WX_004_0019arr_1010", arr1);
                            model.addAttribute("BeforeDate_1010", arr2);
                            model.addAttribute("Comparedatecolumn_1010", Comparedatecolumn(beforedate, contrastday, sdate));
                            model.addAttribute("CompareprovinceArr_1010", StrArr(CompareprovinceArr(LWX004.get(i) + "", beforedate, contrastday, sdate, day)));
                            model.addAttribute("ResultsArr_1010", ResultsArr2(arr1, arr2));
                            model.addAttribute("ResultsArr1_1010", ResultsArr1);

                            String Arr2[][] = StrArr2("WX_004_1010", contrastday, coldateArr, Comparedatecolumn(beforedate, contrastday, sdate), arr1, arr2, ResultsArr2(arr1, arr2), StrArr(ProvinceArr(user2, coldateArr, isdaynull(day) + 1)), StrArr(CompareprovinceArr(LWX004.get(i) + "", beforedate, contrastday, sdate, day)), ResultsArr1);

                            ListArr.add(Arr2);
                        }
                        if (LWX004.get(i).equals("WX_004_0020")) {
                            System.out.println(":WX_004_0020");
                            model.addAttribute("user2_0020", StrArr(ProvinceArr(user2, coldateArr, isdaynull(day) + 1)));
                            model.addAttribute("WX_004_0019arr_0020", arr1);
                            model.addAttribute("BeforeDate_0020", arr2);
                            model.addAttribute("Comparedatecolumn_0020", Comparedatecolumn(beforedate, contrastday, sdate));
                            model.addAttribute("CompareprovinceArr_0020", StrArr(CompareprovinceArr(LWX004.get(i) + "", beforedate, contrastday, sdate, day)));
                            model.addAttribute("ResultsArr_0020", ResultsArr2(arr1, arr2));
                            model.addAttribute("ResultsArr1_0020", ResultsArr1);

                            String Arr2[][] = StrArr2("WX_004_0020", contrastday, coldateArr, Comparedatecolumn(beforedate, contrastday, sdate), arr1, arr2, ResultsArr2(arr1, arr2), StrArr(ProvinceArr(user2, coldateArr, isdaynull(day) + 1)), StrArr(CompareprovinceArr(LWX004.get(i) + "", beforedate, contrastday, sdate, day)), ResultsArr1);

                            ListArr.add(Arr2);
                        }
                        if (LWX004.get(i).equals("WX_004_1011")) {
                            System.out.println(":WX_004_1011");
                            model.addAttribute("user2_1011", StrArr(ProvinceArr(user2, coldateArr, isdaynull(day) + 1)));
                            model.addAttribute("WX_004_0019arr_1011", arr1);
                            model.addAttribute("BeforeDate_1011", arr2);
                            model.addAttribute("Comparedatecolumn_1011", Comparedatecolumn(beforedate, contrastday, sdate));
                            model.addAttribute("CompareprovinceArr_1011", StrArr(CompareprovinceArr(LWX004.get(i) + "", beforedate, contrastday, sdate, day)));
                            model.addAttribute("ResultsArr_1011", ResultsArr2(arr1, arr2));
                            model.addAttribute("ResultsArr1_1011", ResultsArr1);

                            String Arr2[][] = StrArr2("WX_004_1011", contrastday, coldateArr, Comparedatecolumn(beforedate, contrastday, sdate), arr1, arr2, ResultsArr2(arr1, arr2), StrArr(ProvinceArr(user2, coldateArr, isdaynull(day) + 1)), StrArr(CompareprovinceArr(LWX004.get(i) + "", beforedate, contrastday, sdate, day)), ResultsArr1);

                            ListArr.add(Arr2);
                        }
                        if (LWX004.get(i).equals("WX_004_1012")) {
                            System.out.println(":WX_004_1012");
                            model.addAttribute("user2_1012", StrArr(ProvinceArr(user2, coldateArr, isdaynull(day) + 1)));
                            model.addAttribute("WX_004_0019arr_1012", arr1);
                            model.addAttribute("BeforeDate_1012", arr2);
                            model.addAttribute("Comparedatecolumn_1012", Comparedatecolumn(beforedate, contrastday, sdate));
                            model.addAttribute("CompareprovinceArr_1012", StrArr(CompareprovinceArr(LWX004.get(i) + "", beforedate, contrastday, sdate, day)));
                            model.addAttribute("ResultsArr_1012", ResultsArr2(arr1, arr2));
                            model.addAttribute("ResultsArr1_1012", ResultsArr1);

                            String Arr2[][] = StrArr2("WX_004_1012", contrastday, coldateArr, Comparedatecolumn(beforedate, contrastday, sdate), arr1, arr2, ResultsArr2(arr1, arr2), StrArr(ProvinceArr(user2, coldateArr, isdaynull(day) + 1)), StrArr(CompareprovinceArr(LWX004.get(i) + "", beforedate, contrastday, sdate, day)), ResultsArr1);

                            ListArr.add(Arr2);
                        }


                    } else {

                        //判断日期输入的格式是否正确
                        String dateTF = isnull(date);
                        model.addAttribute("dateTF", dateTF);
                        //页面显示的当前时间
                        model.addAttribute("nowdate", "null");
                    }

                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("执行！！！！！！！！！！！！！");
            return "testindex2"; 
        }


        //返回到testindex.ftlh


    }

    /**
     * 多个 二维数组 合并
     */
    public static String[][] Arr2(List<String[][]> arr) {

        String strs1[][] = new String[][]{{"表格"}};
        String resoutArr[][] = new String[][]{};

        if (arr.size() == 5) {
            resoutArr = unite(arr.get(4), unite(arr.get(3), unite(arr.get(2), unite(arr.get(1), unite(arr.get(0), strs1)))));

        }
        if (arr.size() == 4) {
            resoutArr = unite(arr.get(3), unite(arr.get(2), unite(arr.get(1), unite(arr.get(0), strs1))));

        }
        if (arr.size() == 3) {
            resoutArr = unite(arr.get(2), unite(arr.get(1), unite(arr.get(0), strs1)));

        }
        if (arr.size() == 2) {
            resoutArr = unite(arr.get(1), unite(arr.get(0), strs1));

        }
        if (arr.size() == 1) {
            resoutArr = unite(arr.get(0), strs1);

        }

        return resoutArr;


    }


    /**
     * 两个二维数组 合并一个
     */
    public static String[][] unite(String[][] os1, String[][] os2) {
        List<String[]> list = new ArrayList<String[]>(Arrays.<String[]>asList(os1));
        list.addAll(Arrays.<String[]>asList(os2));
        return list.toArray(os1);
    }

    /**
     * 传入二维数组 下载Excel
     *
     * @param Arr
     * @param response
     * @throws IOException
     */
    public static void ArrWriteExcel(String[][] Arr, HttpServletResponse response) throws IOException {


        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生成一个表格，设置表格名称为"学生表"
        HSSFSheet sheet = workbook.createSheet("学生表");
        //设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(10);
        for (int i = 0; i < Arr.length; i++) {
            HSSFRow row = sheet.createRow(i);
            for (int j = 0; j < Arr[i].length; j++) {
                //创建一个内容对象
                HSSFRichTextString text = new HSSFRichTextString(Arr[i][j]);
                //创建一个单元格,将内容对象的文字内容写入到单元格中
                row.createCell(j).setCellValue(text);
            }
        }
        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");
        //这后面可以设置导出Excel的名称，此例中名为student.xls
        response.setHeader("Content-disposition", "attachment;filename=student.xls");
        //刷新缓冲

        response.flushBuffer();
        //workbook将Excel写入到response的输出流中，供页面下载
        workbook.write(response.getOutputStream());


    }

    /**
     * 多个数组 整理成一个二维数组
     */
    public static String[][] StrArr2(
            String WX,
            int contrastday,
            String coldateArr[],
            String Comparedatecolumn_0019[],
            String WX_004_0019arr_0019[][],
            String BeforeDate_0019[][],
            String ResultsArr_0019[][],
            String user2_0019[],
            String CompareprovinceArr_0019[],
            String ResultsArr1_0019[]
    ) {


        System.out.println("-------------------测试--------------------");
//        System.out.println("行"+((contrastday+1)*3+1));
//        System.out.println("列"+11);


        String Arr[][] = new String[11][((contrastday + 1) * 3 + 1)];
//                    coldateArr--Comparedatecolumn_0019--同比增幅
//                    WX_004_0019arr_0019--BeforeDate_0019--ResultsArr_0019
//                    user2_0019--CompareprovinceArr_0019--ResultsArr1_0019


        for (int j = 0; j < Arr.length; j++) {
            for (int k = 0; k < Arr[j].length; k++) {
                Arr[j][k] = "-";
            }
        }
        Arr[0][0] = WX;
        Arr[1][0] = "福州";
        Arr[2][0] = "厦门";
        Arr[3][0] = "宁德";
        Arr[4][0] = "莆田";
        Arr[5][0] = "泉州";
        Arr[6][0] = "漳州";
        Arr[7][0] = "龙岩";
        Arr[8][0] = "三明";
        Arr[9][0] = "南平";
        Arr[10][0] = "全省";


        for (int j = 1; j <= contrastday + 1; j++) {
            Arr[0][j] = coldateArr[j - 1];
        }
        for (int j = contrastday + 2; j <= (contrastday + 1) * 2; j++) {
            Arr[0][j] = Comparedatecolumn_0019[j - contrastday - 2];
        }
        for (int j = (contrastday + 1) * 2 + 1; j <= (contrastday + 1) * 3; j++) {
            Arr[0][j] = "同比增幅";
        }

        for (int j = 0; j < WX_004_0019arr_0019.length; j++) {
            for (int k = 0; k < WX_004_0019arr_0019[j].length; k++) {
                Arr[j + 1][k + 1] = WX_004_0019arr_0019[j][k];
            }
        }

        for (int j = 0; j < BeforeDate_0019.length; j++) {
            for (int k = 0; k < BeforeDate_0019[j].length; k++) {
                Arr[j + 1][k + contrastday + 2] = BeforeDate_0019[j][k];
            }
        }
        for (int j = 0; j < 9; j++) {
            for (int k = 0; k < contrastday + 1; k++) {
                Arr[j + 1][k + ((contrastday + 1) * 2 + 1)] = ResultsArr_0019[j][k];
//                            Arr[j+1][k+((contrastday+1)*2+1)]="#";
//                            System.out.printf(ResultsArr2(arr1,arr2)[j][k]);
            }
//                        System.out.println();
        }


        for (int j = 1; j <= contrastday + 1; j++) {
            Arr[10][j] = user2_0019[j - 1];
        }
        for (int j = contrastday + 2; j <= (contrastday + 1) * 2; j++) {
            Arr[10][j] = CompareprovinceArr_0019[j - contrastday - 2];
        }
        for (int j = (contrastday + 1) * 2 + 1; j <= (contrastday + 1) * 3; j++) {
            Arr[10][j] = ResultsArr1_0019[j - ((contrastday + 1) * 2 + 1)];
        }


//        for (int j = 0; j < Arr.length; j++) {
//            for (int k = 0; k <Arr[j].length ; k++) {
//                System.out.printf(Arr[j][k]);
//            }
//            System.out.println();
//        }

        return Arr;
    }


    /**
     * 0.0 转换 '-'
     */
    public static String[] StrArr(String Str[]) {
        for (int i = 0; i < Str.length; i++) {
            if (Str[i].equals("0.0")) {
                Str[i] = "-";
            }
        }
        return Str;
    }

    /**
     * 多选框 判断
     */
    public List checkboxs(String WX_004_0019, String WX_004_1010, String WX_004_0020, String WX_004_1011, String WX_004_1012) {
        List LWX004 = new ArrayList();
        LWX004.add("WX_004_0019");
        if (!WX_004_1010.equals("")) {
            LWX004.add(WX_004_1010);
        }
        if (!WX_004_0020.equals("")) {
            LWX004.add(WX_004_0020);
        }
        if (!WX_004_1011.equals("")) {
            LWX004.add(WX_004_1011);
        }
        if (!WX_004_1012.equals("")) {
            LWX004.add(WX_004_1012);
        }
        for (int i = 0; i < LWX004.size(); i++) {
            System.out.println(LWX004.get(i));
        }

        return LWX004;


    }

    /**
     * 数据对比计算后结果
     * 返回二维数组
     */
    public static String[][] ResultsArr2(String arr1[][], String arr2[][]) {

        //获取两个数组
        String[][] arrs1 = new String[arr1.length][arr1.length];
        String[][] arrs2 = new String[arr1.length][arr1.length];


        Double[][] Resultintarr1 = new Double[arr1.length][arr1.length];


        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[i].length; j++) {
                arrs1[i][j] = arr1[i][j];
                arrs2[i][j] = arr2[i][j];

                if (arrs1[i][j].equals("-")) {
                    arrs1[i][j] = "0";
                }
                if (arrs2[i][j].equals("-")) {
                    arrs2[i][j] = "0";
                }

                if (Double.parseDouble(arrs2[i][j]) == 0) {
                    Resultintarr1[i][j] = Double.valueOf(0);
                } else {
                    Resultintarr1[i][j] = ((Double.parseDouble(arrs1[i][j]) - Double.parseDouble(arrs2[i][j])) / (Double.parseDouble(arrs2[i][j])) * 100);
                }
            }
        }


        String[][] Resultintarrs = new String[arr1.length][arr1.length];

        for (int i = 0; i < Resultintarrs.length; i++) {
            for (int j = 0; j < Resultintarrs[i].length; j++) {

                Resultintarrs[i][j] = Resultintarr1[i][j] + "";
//                System.out.println(Resultintarr1[i][j]);
                if (Resultintarrs[i][j].equals("0.0")) {
                    Resultintarrs[i][j] = "-";
                } else if (Resultintarrs[i][j].equals("null")) {
                    Resultintarrs[i][j] = "-";
//                    System.out.println("-----------------");
                } else {
                    DecimalFormat df = new DecimalFormat("#.00");
//                System.out.println(Resultintarrs[i][j]);
                    Resultintarrs[i][j] = df.format(Double.parseDouble(String.valueOf(Resultintarr1[i][j]))) + "";
                }


            }
        }
        return Resultintarrs;
    }

    /**
     * 数据对比计算结果 全省
     * 返回一维数组
     */
    public static String[] ResultsArr1(String arr1[], String arr2[]) {
        String arrs1[] = new String[arr1.length];
        String arrs2[] = new String[arr1.length];

        Double Resultintarr1[] = new Double[arr1.length];

        for (int i = 0; i < arr1.length; i++) {
            arrs1[i] = arr1[i];
            arrs2[i] = arr2[i];

            if (arrs1[i].equals("-")) {
                arrs1[i] = "0.0";
            }
            if (arrs2[i].equals("-")) {
                arrs2[i] = "0.0";
            }


            if (Double.parseDouble(arrs2[i]) == 0) {
                Resultintarr1[i] = 0.0;
            } else {
                Resultintarr1[i] = ((Double.parseDouble(arrs1[i]) - Double.parseDouble(arrs2[i])) / (Double.parseDouble(arrs2[i])) * 100);
            }

//            System.out.printf(String.valueOf(Resultintarr1[i]));
        }

        String Resultintarrs[] = new String[arr1.length];
        for (int i = 0; i < Resultintarrs.length; i++) {

            Resultintarrs[i] = Resultintarr1[i] + "";
            if (Resultintarrs[i].equals("0.0")) {
                Resultintarrs[i] = "-";
            } else if (Resultintarrs[i].equals("null")) {
                Resultintarrs[i] = "-";
            } else {
                DecimalFormat df = new DecimalFormat("#.00");
//                System.out.println(Resultintarrs[i][j]);
                Resultintarrs[i] = df.format(Double.parseDouble(String.valueOf(Resultintarr1[i]))) + "";

            }

        }


        return Resultintarrs;
    }

    /**
     * 对比晚年的日期是否为空
     * 把查询到的数据存放在二维数组
     * 返回二维数组
     */
    public String[][] BeforeDate(String WX_004_XXXX, String beforedate, int contrastday, String date) {
        if (beforedate == null || "".equals(beforedate)) {
            //没有输入日期 默认当前日期的前一年
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // 将字符串的日期转为Date类型，ParsePosition(0)表示从第一个字符开始解析
            Date d = sdf.parse(date, new ParsePosition(0));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);
            // add方法中的第二个参数n中，正数表示该日期后n天，负数表示该日-1期的前n天
            calendar.add(Calendar.YEAR, -1);
            Date YEAR = calendar.getTime();
            String out = sdf.format(YEAR);
            List<User> all = userService.SelectAll(out, contrastday);
            String WX_004_0019arr[][] = new String[CITY_ID().length][contrastday + 1];
            String out1 = userdate(out, contrastday);
            int CId[] = CITY_ID59();
            String coldateArr[] = dateSplit(out, out1);

            String arr2[][] = transformationArr(dataarrangement(WX_004_XXXX, contrastday, CId, all, initializationArr(WX_004_0019arr, contrastday), coldateArr), CId, contrastday);
            return arr2;

        } else {

            //有输入日期
            List<User> all = userService.SelectAll(beforedate, contrastday);
            String WX_004_0019arr[][] = new String[CITY_ID().length][contrastday + 1];
            String out1 = userdate(beforedate, contrastday);
            int CId[] = CITY_ID59();
            String coldateArr[] = dateSplit(beforedate, out1);
            String arr2[][] = transformationArr(dataarrangement(WX_004_XXXX, contrastday, CId, all, initializationArr(WX_004_0019arr, contrastday), coldateArr), CId, contrastday);
            return arr2;

        }

    }

    /**
     * 对比的日期栏
     * 返回数组
     */
    public static String[] Comparedatecolumn(String beforedate, int contrastday, String date) {

        if (beforedate == null || "".equals(beforedate)) {

            //没有输入日期 默认当前日期的前一年
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // 将字符串的日期转为Date类型，ParsePosition(0)表示从第一个字符开始解析
            Date d = sdf.parse(date, new ParsePosition(0));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);
            // add方法中的第二个参数n中，正数表示该日期后n天，负数表示该日-1期的前n天
            calendar.add(Calendar.YEAR, -1);
            Date YEAR = calendar.getTime();
            String out = sdf.format(YEAR);
            String out1 = userdate(out, contrastday);
            String coldateArr[] = dateSplit(out, out1);


            return coldateArr;

        } else {

            //有输入日期
            String out1 = userdate(beforedate, contrastday);
            String coldateArr[] = dateSplit(beforedate, out1);
            return coldateArr;

        }

    }

    /**
     * 对比的全省总和过滤
     */
    public String[] CompareprovinceArr(String WX_004_XXXX, String beforedate, int contrastday, String date, String day) {

        if (beforedate == null || "".equals(beforedate)) {
            //没有输入日期 默认当前日期的前一年
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // 将字符串的日期转为Date类型，ParsePosition(0)表示从第一个字符开始解析
            Date d = sdf.parse(date, new ParsePosition(0));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);
            // add方法中的第二个参数n中，正数表示该日期后n天，负数表示该日-1期的前n天
            calendar.add(Calendar.YEAR, -1);
            Date YEAR = calendar.getTime();
            String out = sdf.format(YEAR);
            String out1 = userdate(out, contrastday);
            String coldateArr[] = dateSplit(out, out1);
//          ##########
            List<User2> user2 = ProvinceSelect(WX_004_XXXX, out, out1);
//            System.out.println("------------测试打印4-------------");
//            System.out.println(user2);

            String dateall[] = ProvinceArr(user2, coldateArr, isdaynull(day) + 1);


            return dateall;
        } else {
            String out1 = userdate(beforedate, contrastday);
            String coldateArr[] = dateSplit(beforedate, out1);


//            ######################
            List<User2> user2 = ProvinceSelect(WX_004_XXXX, beforedate, out1);
//            System.out.println("------------测试打印4-------------");
//            System.out.println(user2);


            String dateall[] = ProvinceArr(user2, coldateArr, isdaynull(day) + 1);

            return dateall;
        }
    }

    /**
     * 判断用户输入的对比天数是否为空
     */
    public static int isdaynull(String day) {
        int contrastday;

        if (day.equals("")) {
            contrastday = 7;
        } else {

            contrastday = Integer.parseInt(day);
            if (contrastday > 7 || contrastday < 1) {
                contrastday = 7;
            } else {
                contrastday = Integer.parseInt(day);
            }

        }

        return --contrastday;
    }

    /**
     * 全省总和
     *
     * @param nowdate    当前时间
     * @param beforedate 7天后时间
     * @return
     */
    public List<User2> ProvinceSelect(String WX_004_XXXX, String nowdate, String beforedate) {
        List<User2> user2 = userService.ProvinceSelect(WX_004_XXXX, nowdate, beforedate);
        return user2;
    }

    /**
     * 全省总和过滤
     * 时间对比后 返回数组
     */
    public static String[] ProvinceArr(List<User2> user2, String coldateArr[], int contrastday) {

        String d1[] = new String[contrastday];
        for (int i = 0; i < d1.length; i++) {
            d1[i] = "-";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i2 = 0; i2 < contrastday; i2++) {
            for (int j = 0; j < user2.size(); j++) {
                User2 user = user2.get(j);
                if (coldateArr[i2].equals(sdf.format(user.getDATA_TIME()))) {
                    d1[i2] = String.valueOf(user.getWX_004_0019());
                }
            }
        }

        return d1;
    }

    /**
     * 返回日期格式是否正确
     *
     * @param date
     * @return
     */
    public String isnull(String date) {

        String date_string = date;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateflag = "true";
        try {
            Date dateTF = format.parse(date_string);
        } catch (ParseException e) {
            dateflag = "false";
        } finally {
        }

        return dateflag;
    }

    /**
     * 返回指定的时间段内的有所日期
     *
     * @param startDate 开始时间,例:2016-02-25
     * @param endDate   结束时间,例:2016-03-05
     * @return
     */
    private static String[] dateSplit(String startDate, String endDate) {
        String[] ar = {};
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date newstartDate;
        try {
            newstartDate = simpleDateFormat.parse(startDate);
            Date newendDate = simpleDateFormat.parse(endDate);
            List<String> dateList = new ArrayList<String>();
            Long spi = newendDate.getTime() - newstartDate.getTime();
            Long step = spi / (24 * 60 * 60 * 1000);// 相隔天数
            List<Date> newdateList = new ArrayList<Date>();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            newdateList.add(newstartDate);
            dateList.add(startDate);
            for (int i = 1; i <= step; i++) {
                dateList.add(formatter.format(new Date(newdateList.get(i - 1).getTime() + (24 * 60 * 60 * 1000))));
                newdateList.add(new Date(newdateList.get(i - 1).getTime() + (24 * 60 * 60 * 1000)));// 比上一天+一
            }
            String[] strings = new String[dateList.size()];
            String[] array = dateList.toArray(strings);
            return array;
        } catch (ParseException e) {
            e.printStackTrace();
            return ar;
        }
    }

    /**
     * 城市编码
     *
     * @return
     */
    public static int[] CITY_ID59() {
        return new int[]{591, 592, 593, 594, 595, 596, 597, 598, 599};
    }

    /**
     * 返回城市数组
     *
     * @return
     */
    public static String[] CITY_ID() {
        return new String[]{"福州", "厦门", "宁德", "莆田", "泉州", "漳州", "龙岩", "三明", "南平"};
    }

    /**
     * 数组初始化
     */
    public static String[][] initializationArr(String[][] WX_004_0019arr, int contrastday) {
        for (int i1 = 0; i1 < 9; i1++) {
            for (int j1 = 0; j1 <= contrastday; j1++) {
                WX_004_0019arr[i1][j1] = "-";
            }
        }
        return WX_004_0019arr;
    }

    /**
     * 数组 如果有null 替换‘-’
     *
     * @param WX_004_0019arr
     * @return
     */
    public static String[][] transformationArr(String[][] WX_004_0019arr, int CId[], int contrastday) {
        for (int i1 = 0; i1 < CId.length; i1++) {
            for (int j1 = 0; j1 <= contrastday; j1++) {
                if (WX_004_0019arr[i1][j1].equals("null")) {
                    WX_004_0019arr[i1][j1] = "-";
                }
            }
        }
        return WX_004_0019arr;
    }

    /**
     * 表格中的数据 时间对比
     * 对比后 数据存入二维数组中
     * // CId[] all WX_004_0019arr[][]
     */
    public static String[][] dataarrangement(String WX_004_XXXX, int contrastday, int CId[], List<User> all, String WX_004_0019arr[][], String coldateArr[]) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < CId.length; i++) {
            for (int j = 0; j < all.size(); j++) {
                User user = all.get(j);
                if (user.getCITY_ID() == (CId[i])) {
                    for (int k = 0; k <= contrastday; k++) {
                        boolean TF = false;
                        if (sdf.format(user.getDATA_TIME()).equals(coldateArr[k])) {
                            TF = true;
                        } else {

                        }
                        if (TF) {
                            if (WX_004_XXXX.equals("WX_004_0019")) {
                                WX_004_0019arr[i][k] = String.valueOf(user.getWX_004_0019());
                            } else if (WX_004_XXXX.equals("WX_004_1010")) {
                                WX_004_0019arr[i][k] = String.valueOf(user.getWX_004_1010());
                            } else if (WX_004_XXXX.equals("WX_004_0020")) {
                                WX_004_0019arr[i][k] = String.valueOf(user.getWX_004_0020());
                            } else if (WX_004_XXXX.equals("WX_004_1011")) {
                                WX_004_0019arr[i][k] = String.valueOf(user.getWX_004_1011());
                            } else {
                                WX_004_0019arr[i][k] = String.valueOf(user.getWX_004_1012());
                            }

                        }
                    }
                }
            }
        }
        return WX_004_0019arr;
    }

    /**
     * 传入用户输入的时间
     * 返回n后的时间
     */
    public static String userdate(String nowdate, int contrastday) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 将字符串的日期转为Date类型，ParsePosition(0)表示从第一个字符开始解析

        Date d = sdf.parse(nowdate, new ParsePosition(0));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        // add方法中的第二个参数n中，正数表示该日期后n天，负数表示该日期的前n天
        calendar.add(Calendar.DATE, contrastday);
        Date date1 = calendar.getTime();
        String out = sdf.format(date1);
        return out;
    }


}
