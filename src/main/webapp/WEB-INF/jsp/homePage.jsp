<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style type="text/css">
	<!--<%@ include file="/WEB-INF/mycss/homePage.css"%>-->
	<!--<%@ include file="/WEB-INF/mycss/main.css"%>-->
</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DBMS</title>
</head>
<body>
	<div id="left">
	<section class="contenedor">
	<nav>
		<ul class="menu-arbol">
			<li>
				<input type="radio" name="nivel-1" class="mostrar-menu" id="menu1">
				<label for="menu1" class="ampliar"></label>
				<a href="">menu1</a>
				<ul class="nivel-01">
					<li>
						<input type="checkbox" class="mostrar-menu" id="menu01">
						<label for="menu01" class="ampliar"></label>
						<a href="">menu1</a>
						<ul class="nivel-02">
							<li>
								<input type="checkbox" class="mostrar-menu" id="menu011">
								<label for="menu011" class="ampliar"></label>
								<a href="">menu1</a>
								<ul class="nivel-03">
									<li>
										<input type="checkbox" class="mostrar-menu" id="menu0111">
										<label for="menu0111" class="ampliar"></label>
										<a href="">menu1</a>
										<ul class="nivel-04">
											<li><a href="">enlace</a></li>
											<li><a href="">enlace</a></li>
										</ul>
									</li>
									<li>
										<input type="checkbox" class="mostrar-menu" id="menu0112">
										<label for="menu0112" class="ampliar"></label>
										<a href="">menu2</a>
										<ul class="nivel-04">
											<li><a href="">enlace</a></li>
											<li><a href="">enlace</a></li>
										</ul>
									</li>
								</ul>
							</li>
							<li>
								<input type="checkbox" class="mostrar-menu" id="menu012">
								<label for="menu012" class="ampliar"></label>
								<a href="">menu2</a>
								<ul class="nivel-03">
									<li>
										<input type="checkbox" class="mostrar-menu" id="menu0121">
										<label for="menu0121" class="ampliar"></label>
										<a href="">menu1</a>
										<ul class="nivel-04">
											<li><a href="">enlace</a></li>
											<li><a href="">enlace</a></li>
										</ul>
									</li>
									<li>
										<input type="checkbox" class="mostrar-menu" id="menu0122">
										<label for="menu0122" class="ampliar"></label>
										<a href="">menu2</a>
										<ul class="nivel-04">
											<li><a href="">enlace</a></li>
											<li><a href="">enlace</a></li>
										</ul>
									</li>
								</ul>
							</li>
						</ul>
					</li>
					<li>
						<input type="checkbox" class="mostrar-menu" id="menu02">
						<label for="menu02" class="ampliar"></label>
						<a href="">menu2</a>
						<ul class="nivel-02">
							<li>
								<input type="checkbox" class="mostrar-menu" id="menu021">
								<label for="menu021" class="ampliar"></label>
								<a href="">menu1</a>
								<ul class="nivel-03">
									<li>
										<input type="checkbox" class="mostrar-menu" id="menu0211">
										<label for="menu0211" class="ampliar"></label>
										<a href="">menu1</a>
										<ul class="nivel-04">
											<li><a href="">enlace</a></li>
											<li><a href="">enlace</a></li>
										</ul>
									</li>
									<li>
										<input type="checkbox" class="mostrar-menu" id="menu0212">
										<label for="menu0212" class="ampliar"></label>
										<a href="">menu2</a>
										<ul class="nivel-04">
											<li><a href="">enlace</a></li>
											<li><a href="">enlace</a></li>
										</ul>
									</li>
								</ul>
							</li>
							<li>
								<input type="checkbox" class="mostrar-menu" id="menu022">
								<label for="menu022" class="ampliar"></label>
								<a href="">menu2</a>
								<ul class="nivel-03">
									<li>
										<input type="checkbox" class="mostrar-menu" id="menu0221">
										<label for="menu0221" class="ampliar"></label>
										<a href="">menu1</a>
										<ul class="nivel-04">
											<li><a href="">enlace</a></li>
											<li><a href="">enlace</a></li>
										</ul>
									</li>
									<li>
										<input type="checkbox" class="mostrar-menu" id="menu0222">
										<label for="menu0222" class="ampliar"></label>
										<a href="">menu2</a>
										<ul class="nivel-04">
											<li><a href="">enlace</a></li>
											<li><a href="">enlace</a></li>
										</ul>
									</li>
								</ul>
							</li>
						</ul>
					</li>
				</ul>
			</li>
		</ul>
	</nav>	 
	</section>
	</div>
	<div id="main">
	</div>
	<div id="footer"></div>
</body>
</html>