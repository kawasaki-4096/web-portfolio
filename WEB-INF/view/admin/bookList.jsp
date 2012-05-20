<s:layout-render name="/WEB-INF/view/layout/default.jsp">
    <s:layout-component name="contents">
		<ul>
			<c:forEach var="oneWork" items="${actionBean.works }">
				<li class="grid150px tc" ><a href="${ctx }${oneWork.linkUrl }"> 
				<img src="${ctx }/images/thumbs/${oneWork.imageData.imagePath }${oneWork.imageData.thumbnailName }" alt="" style="width: 150px; height: 100px;" /> 
				</a></li>
			</c:forEach>
		</ul>
		<div><s:link beanclass="com.studio4096.portfolio.action.admin.BookEditActionBean">new book</s:link></div>
	</s:layout-component>
</s:layout-render>
