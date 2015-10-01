package org.kayura.web.easyui.tags;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 枫 on 2014/8/6.
 */
public class DataGridTag extends TagRender {

	private static final long serialVersionUID = 6895207019657066964L;
	private Boolean fit;
    private String classStyle;
    private String style;

    private String title;
    private Object data;
    private Boolean fitColumns;
    private String resizeHandle;
    private Boolean autoRowHeight;
    private String toolbar;
    private Boolean striped;
    private String method;
    private Boolean nowrap;
    private String idField;
    private String url;
    private String loadMsg;
    private Boolean pagination;
    private Boolean rownumbers;
    private Boolean singleSelect;
    private Boolean ctrlSelect;
    private Boolean checkOnSelect;
    private Boolean selectOnCheck;
    private String pagePosition;
    private int pageNumber = -1;
    private int pageSize = 30;
    private Object pageList;
    private Map<String, String> queryParams;
    private String sortName;
    private String sortOrder;
    private Boolean multiSort;
    private Boolean remoteSort;
    private Boolean showHeader;
    private Boolean showFooter;
    private int scrollbarSize = -1;


    @Override
    public String getEasyuiTag() {
        return "datagrid";
    }

    @Override
    public int renderBody() {
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public Map<String, Object> getOptions() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("fit", getFit());
        options.put("fitColumns", getFitColumns());
        options.put("resizeHandle", getResizeHandle());
        options.put("autoRowHeight", getAutoRowHeight());
        options.put("toolbar", getToolbar());
        options.put("striped", getStriped());
        options.put("method", getMethod());
        options.put("nowrap", getNowrap());
        options.put("idField", getIdField());
        options.put("url", getUrl());
        options.put("data", getData());
        options.put("loadMsg", getLoadMsg());
        options.put("pagination", getPagination());
        options.put("rownumbers", getRownumbers());
        options.put("singleSelect", getSingleSelect());
        options.put("ctrlSelect", getCtrlSelect());
        options.put("checkOnSelect", getCheckOnSelect());
        options.put("selectOnCheck", getSelectOnCheck());
        options.put("pagePosition", getPagePosition());
        options.put("pageNumber", getPageNumber());
        options.put("pageSize", getPageSize());
        if(getPageList()!=null){
            Object pl = getPageList();
            if(pl instanceof String){
                String[] pList = ((String) pl).split(",");
                int[] pArray =new int[pList.length];
                for(int i=0; i < pList.length; i++){
                    pArray[i] = Integer.valueOf(pList[i]);
                }
                options.put("pageList", pArray);
            }else if (pl instanceof int[]){
                options.put("pageList", pl);
            }
        }
        options.put("queryParams", getQueryParams());
        options.put("sortName", getSortName());
        options.put("sortOrder", getSortOrder());
        options.put("multiSort", getMultiSort());
        options.put("remoteSort", getRemoteSort());
        options.put("showHeader", getShowHeader());
        options.put("showFooter", getShowFooter());
        options.put("scrollbarSize", getScrollbarSize());
        return options;
    }

    @Override
    public void renderStart(JspWriter out) {
        try {
            out.println("<table");
            if (getId() != null) {
                out.write(" id=\"" + getId() + "\"");
            }
            if (getClassStyle() != null) {
                out.write(" class=\"" + getClassStyle() + "\"");
            }
            if (getStyle() != null) {
                out.write(" style=\"" + getStyle() + "\"");
            }
            if (getTitle() != null) {
                out.write(" title=\"" + getTitle() + "\"");
            }
            out.write(" data-options=\"");
            out.write(optionsToString());
            out.write("\"");
            out.write(">");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void renderEnd(JspWriter out) {
        try {
            out.println("</table>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean getFit() {
        return fit;
    }

    public void setFit(Boolean fit) {
        this.fit = fit;
    }

    public String getClassStyle() {
        return classStyle;
    }

    public void setClassStyle(String classStyle) {
        this.classStyle = classStyle;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Boolean getFitColumns() {
        return fitColumns;
    }

    public void setFitColumns(Boolean fitColumns) {
        this.fitColumns = fitColumns;
    }

    public String getResizeHandle() {
        return resizeHandle;
    }

    public void setResizeHandle(String resizeHandle) {
        this.resizeHandle = resizeHandle;
    }

    public Boolean getAutoRowHeight() {
        return autoRowHeight;
    }

    public void setAutoRowHeight(Boolean autoRowHeight) {
        this.autoRowHeight = autoRowHeight;
    }

    public String getToolbar() {
        return toolbar;
    }

    public void setToolbar(String toolbar) {
        this.toolbar = toolbar;
    }

    public Boolean getStriped() {
        return striped;
    }

    public void setStriped(Boolean striped) {
        this.striped = striped;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Boolean getNowrap() {
        return nowrap;
    }

    public void setNowrap(Boolean nowrap) {
        this.nowrap = nowrap;
    }

    public String getIdField() {
        return idField;
    }

    public void setIdField(String idField) {
        this.idField = idField;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLoadMsg() {
        return loadMsg;
    }

    public void setLoadMsg(String loadMsg) {
        this.loadMsg = loadMsg;
    }

    public Boolean getPagination() {
        return pagination;
    }

    public void setPagination(Boolean pagination) {
        this.pagination = pagination;
    }

    public Boolean getRownumbers() {
        return rownumbers;
    }

    public void setRownumbers(Boolean rownumbers) {
        this.rownumbers = rownumbers;
    }

    public Boolean getSingleSelect() {
        return singleSelect;
    }

    public void setSingleSelect(Boolean singleSelect) {
        this.singleSelect = singleSelect;
    }

    public Boolean getCtrlSelect() {
        return ctrlSelect;
    }

    public void setCtrlSelect(Boolean ctrlSelect) {
        this.ctrlSelect = ctrlSelect;
    }

    public Boolean getCheckOnSelect() {
        return checkOnSelect;
    }

    public void setCheckOnSelect(Boolean checkOnSelect) {
        this.checkOnSelect = checkOnSelect;
    }

    public Boolean getSelectOnCheck() {
        return selectOnCheck;
    }

    public void setSelectOnCheck(Boolean selectOnCheck) {
        this.selectOnCheck = selectOnCheck;
    }

    public String getPagePosition() {
        return pagePosition;
    }

    public void setPagePosition(String pagePosition) {
        this.pagePosition = pagePosition;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Object getPageList() {
        return pageList;
    }

    public void setPageList(Object pageList) {
        this.pageList = pageList;
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(Map<String, String> queryParams) {
        this.queryParams = queryParams;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Boolean getMultiSort() {
        return multiSort;
    }

    public void setMultiSort(Boolean multiSort) {
        this.multiSort = multiSort;
    }

    public Boolean getRemoteSort() {
        return remoteSort;
    }

    public void setRemoteSort(Boolean remoteSort) {
        this.remoteSort = remoteSort;
    }

    public Boolean getShowHeader() {
        return showHeader;
    }

    public void setShowHeader(Boolean showHeader) {
        this.showHeader = showHeader;
    }

    public Boolean getShowFooter() {
        return showFooter;
    }

    public void setShowFooter(Boolean showFooter) {
        this.showFooter = showFooter;
    }

    public int getScrollbarSize() {
        return scrollbarSize;
    }

    public void setScrollbarSize(int scrollbarSize) {
        this.scrollbarSize = scrollbarSize;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
