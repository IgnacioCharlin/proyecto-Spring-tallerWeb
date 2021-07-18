<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set  value="<%=request.getContextPath()%>" var="contextPath"  />
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="${contextPath}/css/footer.css">
<link rel="stylesheet" href="${contextPath}/css/themify-icons.css">
<footer class="footer">
        <div class="footer_top">
            <div class="container">
                <div class="row">
                    <div class="col-xl-3 col-md-6 col-lg-3 ">
                        <div class="footer_widget">
                            <div class="footer_logo">
                                <a href="#">
                                  <img src="${contextPath}/images/logo1.png">
                                </a>
                            </div>
                            
                              <div class="socail_links">
                                <ul>
                                    <li>
                                        <a href="#">
                                            <i class="ti-facebook"></i>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="ti-twitter-alt"></i>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="ti-instagram"></i>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="ti-pinterest"></i>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="ti-youtube-play"></i>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            
                            
                            </div>
                            </div>
                            
                             <div class="col-xl-3 col-md-6 col-lg-3 ">
                        <div class="footer_widget">
                             <h3 class="footer_title">
                                Información
                            </h3>
                            
                            <p>Buenos Aires , Palermo <br>Argentina<br>
                                <a href="#">11 37700 680</a> <br>
                                <a href="#">stemenergym@gmail.com</a>
                            </p>
                            <p>



                            </p>
                          
                        </div>
                    </div>
                    <div class="col-xl-3 col-md-6 col-lg-3">
                        <div class="footer_widget">
                            <h3 class="footer_title">
                                Menu
                            </h3>
                       
              				<jsp:include page="menu.jsp" />
                    
                        </div>
                    </div>
                    <div class="col-xl-3 col-md-6 col-lg-3">
                        <div class="footer_widget">
                            <h3 class="footer_title">
                                Subscribite
                            </h3>
                            <form action="#" class="newsletter_form">
                                <input type="text" placeholder="Enter your mail">
                                <button type="submit">Subscribite</button>
                            </form>
                            <p class="newsletter_text">No te pierdas la variedad de clases que ofrecemos.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="copy-right_text">
            <div class="container">
                <div class="footer_border"></div>
                <div class="row">
                    <div class="col-xl-12">
                        <p class="copy_right text-center">
Sitio diseñado y desarrollado por EnerGym Taller Web
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </footer>