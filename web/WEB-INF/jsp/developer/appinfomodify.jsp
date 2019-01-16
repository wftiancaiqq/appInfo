<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<div class="clearfix"></div>
<div class="row">
  <div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
      <div class="x_title">
        <h2>修改APP基础信息 <i class="fa fa-user"></i><small>${sessionScope.devUserSession.devName}</small></h2>
             <div class="clearfix"></div>
      </div>
      <div class="x_content">
        <form class="form-horizontal form-label-left" action="/appInfoDevController/modifyInfo" method="post" enctype="multipart/form-data">
          <input type="hidden" name="id" id="id" value="${sessionScope.map.appInfo.id}">
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="softwareName">软件名称 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input id="softwareName" class="form-control col-md-7 col-xs-12" 
               data-validate-length-range="20" data-validate-words="1" 
               name="softwareName" value="${sessionScope.map.appInfo.softwareName}" required="required"
               placeholder="请输入软件名称" type="text">
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="APKName">APK名称 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input id="APKName" type="text" class="form-control col-md-7 col-xs-12" 
              name="apkName" value="${sessionScope.map.appInfo.apkName}" readonly="readonly">
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="supportROM">支持ROM <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input id="supportROM" class="form-control col-md-7 col-xs-12" 
              	name="supportRom" value="${sessionScope.map.appInfo.supportRom}" required="required"
              	data-validate-length-range="20" data-validate-words="1" 
              	placeholder="请输入支持的ROM" type="text">
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="interfaceLanguage">界面语言 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input id="interfaceLanguage" class="form-control col-md-7 col-xs-12" 
              data-validate-length-range="20" data-validate-words="1"  required="required"
              name="interfaceLanguage" value="${sessionScope.map.appInfo.interfaceLanguage}"
              placeholder="请输入软件支持的界面语言" type="text">
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="softwareSize">软件大小 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input type="number" id="softwareSize" name="softwareSize" value="${sessionScope.map.appInfo.softwareSize}" required="required"
              data-validate-minmax="10,500"  placeholder="请输入软件大小，单位为Mb" class="form-control col-md-7 col-xs-12">
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="downloads">下载次数 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input type="number" id="downloads" name="downloads" value="${sessionScope.map.appInfo.downloads}" required="required"
              data-validate-minmax="10,500"  placeholder="请输入下载次数" class="form-control col-md-7 col-xs-12">
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12"  for="fid">所属平台 <span class="required">*</span></label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input type="hidden" value="${sessionScope.map.appInfo.flatformId}" id="fid" />
              <select name="flatformId" id="flatformId" class="form-control" required="required">
                <option value="0">--请选择--</option>
                <c:forEach items="${sessionScope.map.flatFormList}" var="flat">
                  <option <c:if test="${flat.valueId == sessionScope.map.appInfo.flatformId }">selected</c:if>
                          value="${flat.valueId}">${flat.valueName}</option>
                </c:forEach>
              </select>
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="cl1">一级分类 <span class="required">*</span></label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input type="hidden" value="${sessionScope.map.appInfo.categoryLevel1}" id="cl1" />
              <select name="categoryLevel1" id="categoryLevel1" class="form-control level"  required="required">
                <option value="0">--请选择--</option>
                <c:forEach items="${sessionScope.map.categoryLevel1List}" var="c">
                  <option value="${c.id}" <c:if test="${c.id == sessionScope.map.appInfo.categoryLevel1}"> selected</c:if>>${c.categoryName}</option>
                </c:forEach>
              </select>
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12"  for="cl2">二级分类 <span class="required">*</span></label>
            <div class="col-md-6 col-sm-6 col-xs-12">
            	<input type="hidden" value="${sessionScope.map.appInfo.categoryLevel2}" id="cl2" />
              <select name="categoryLevel2" id="categoryLevel2" class="form-control level"  required="required">
                <option value="-1">--请选择--</option>
                <c:forEach items="${sessionScope.map.categoryLevel2List}" var="c">
                  <option value="${c.id}" <c:if test="${c.id == sessionScope.map.appInfo.categoryLevel2}"> selected</c:if>>${c.categoryName}</option>
                </c:forEach>
              </select>
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="cl3">三级分类 <span class="required">*</span></label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input type="hidden" value="${sessionScope.map.appInfo.categoryLevel3}" id="cl3" />
              <select name="categoryLevel3" id="categoryLevel3" class="form-control level"  required="required">
                <option value="-1">--请选择--</option>
                <c:forEach items="${sessionScope.map.categoryLevel3List}" var="c">
                  <option value="${c.id}" <c:if test="${c.id == sessionScope.map.appInfo.categoryLevel3}"> selected</c:if>>${c.categoryName}</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="statusName">APP状态 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input type="hidden" name="status" value="${sessionScope.map.appInfo.status}">
            	<input id="statusName" type="text" class="form-control col-md-7 col-xs-12" 
              	name="statusName" value="${sessionScope.map.appInfo.statusName}" readonly="readonly">
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="appInfo">应用简介 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <textarea id="appInfo" name="appInfo" required="required"
              placeholder="请输入本软件的相关信息，本信息作为软件的详细信息进行软件的介绍。" class="form-control col-md-7 col-xs-12">
              ${sessionScope.map.appInfo.appInfo}</textarea>
            </div>
          </div>
           <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="attach">LOGO图片 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
				<input type="hidden" id="logoPicPath" name="logoPicPath" value="${sessionScope.map.appInfo.logoPicPath}"/>
            	<input type="hidden" id="logoLocPath" name="logoLocPath" value="${sessionScope.map.appInfo.logoLocPath}"/>
				<div id="uploadfile" style="display: none">
				<input id="attach"  type="file" class="form-control col-md-7 col-xs-12" name="attach" accept="image/jpg,image/png,image/jpeg,image/pneg"/>
				<p><span style="color:red;font-weight: bold;">*注：1、大小不得超过500k.2、图片格式：jpg、png、jpeg、pneg</span></p>
				</div>
				<div id="logoFile"></div>
				${requestScope.fileUploadError }
            </div>
          </div>
          <div class="form-group">
            <div class="col-md-6 col-md-offset-3">
              <c:choose>
                <c:when test="${sessionScope.map.appInfo.status == 3}"><button id="send" type="submit" name="status" value="1" class="btn btn-success">保存并再次提交审核</button></c:when>
                <c:otherwise><button id="send" type="submit" class="btn btn-success">保存</button></c:otherwise>
              </c:choose>
              <button type="button" class="btn btn-primary" id="back">返回</button>
              <br/><br/>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<%@include file="common/footer.jsp"%>
<script src="${pageContext.request.contextPath }/myProjectAssets/localjs/appinfomodify.js"></script>