<s:layout-render name="/WEB-INF/view/layout/default.jsp">
    <s:layout-component name="contents">
		<ul>
			<c:forEach var="oneWork" items="${actionBean.works }">
				<li class="grid20percent vmgn15px tc" ><a href="${ctx }${oneWork.linkUrl }"> 
				<img src="${ctx }/images/thumbs/${oneWork.imageData.imagePath }${oneWork.imageData.thumbnailName }" alt="" style="width: 150px; height: 106px;" /> 
				</a></li>
			</c:forEach>
		</ul>
	</s:layout-component>
</s:layout-render>
