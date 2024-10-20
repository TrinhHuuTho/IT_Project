<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/commons/taglib.jsp"%>
	<c:url value="/" var="URL"></c:url>
<!DOCTYPE html>
<html lang="vi">

<body>
    <!-- Start Preloader -->
    <div id="preload-block">
        <div class="square-block"></div>
    </div>
    <!-- Preloader End -->

    <div class="container-fluid">
        <div class="row">
            <div class="authfy-container col-xs-12 col-sm-10 col-md-8 col-lg-6 col-sm-offset-1 col-md-offset-2 col-lg-offset-3">
                <div class="col-sm-5 authfy-panel-left">
                    <div class="brand-col">
                        <div class="headline">
                            <!-- brand-logo start -->
                            <div class="brand-logo">
<<<<<<< HEAD
                                <a href="login-page.jsp"><img src="${URL}login-page-devforum/images/No-bg logo.png" width="150" alt="brand-logo"></a>
                            </div>
                            <!-- ./brand-logo -->
                            <p>Đăng nhập bằng phương tiện truyền thông xã hội để truy cập nhanh chóng</p>
                            <!-- social login buttons start -->
                            <div class="row social-buttons">
                                <div class="col-xs-4 col-sm-4 col-md-12">
								    <a href="https://www.facebook.com/v7.0/dialog/oauth?
								        scope=email,public_profile&
								        access_type=offline&
								        include_granted_scopes=true&
								        response_type=code&
								        redirect_uri=https://localhost:8443/Project_Sach/authentication/auth/facebook&
								        client_id=859828519474363" 
								        class="btn btn-block btn-facebook">
								        <i class="fa fa-facebook"></i> <span class="hidden-xs hidden-sm">Đăng nhập với Facebook</span>
								    </a>
								</div>
                                <div class="col-xs-4 col-sm-4 col-md-12">
                                    <a href="https://github.com/login/oauth/authorize?scope=user:email,public_profile&access_type=offline&include_granted_scopes=true&response_type=code&redirect_uri=https://localhost:8443/Project_Sach/authentication/auth/github&client_id=Ov23liRlJeDjCQWzTSHR" 
                                    	class="btn btn-block btn-github" >
                                        <i class="fa fa-github"></i> <span class="hidden-xs hidden-sm">Đăng nhập với Github</span>
                                    </a>
                                </div>
                                <!-- Nút Đăng nhập bằng Google -->
								<div class="col-xs-4 col-sm-4 col-md-12">
								    <a href="https://accounts.google.com/o/oauth2/v2/auth?
											scope=email%20profile&
											access_type=offline&
											include_granted_scopes=true&
											response_type=code&
											redirect_uri=https://localhost:8443/Project_Sach/authentication/auth/google&
											client_id=1013728767709-o6q514dv1kk6h8eh3asqsalkdi3tbfud.apps.googleusercontent.com"
								    	class="btn btn-block btn-google">
									    <i class="fa fa-google"></i> <span class="hidden-xs hidden-sm">Đăng nhập với Google</span>
									</a>
								</div>																				                            
=======
                                <a href="login-page.jsp"><img src="${URL}login-page-devforum/images/devforum.png" width="200" alt="brand-logo"></a>
                            </div>
                            <!-- ./brand-logo -->
                            <p>Login using social media to get quick access</p>
                            <!-- social login buttons start -->
                            <div class="row social-buttons">
                                <div class="col-xs-4 col-sm-4 col-md-12">
                                    <a href="#" class="btn btn-block btn-facebook">
                                        <i class="fa fa-facebook"></i> <span class="hidden-xs hidden-sm">Signin with facebook</span>
                                    </a>
                                </div>
                                <div class="col-xs-4 col-sm-4 col-md-12">
                                    <a href="#" class="btn btn-block btn-twitter">
                                        <i class="fa fa-twitter"></i> <span class="hidden-xs hidden-sm">Signin with twitter</span>
                                    </a>
                                </div>
                                <div class="col-xs-4 col-sm-4 col-md-12">
                                    <a href="#" class="btn btn-block btn-google">
                                        <i class="fa fa-google-plus"></i> <span class="hidden-xs hidden-sm">Signin with google</span>
                                    </a>
                                </div>
>>>>>>> origin/HKa
                            </div>
                            <!-- ./social-buttons -->
                        </div>
                    </div>
                </div>
                <div class="col-sm-7 authfy-panel-right">
                    <!-- authfy-login start -->
                    <div class="authfy-login">
                        <!-- panel-login start -->
                        <div class="authfy-panel panel-login text-center active">
                            <div class="authfy-heading">
<<<<<<< HEAD
                                <h3 class="auth-title">Đăng nhập vào tài khoản của bạn</h3>
                                <p>Bạn chưa có tài khoản? <a class="lnk-toggler" data-panel=".panel-signup" href="#">Đăng ký miễn phí!</a></p>
=======
                                <h3 class="auth-title">Login to your account</h3>
                                <p>Don’t have an account? <a class="lnk-toggler" data-panel=".panel-signup" href="#">Sign Up Free!</a></p>
>>>>>>> origin/HKa
                            </div>
                            <div class="row">
                                <div class="col-xs-12 col-sm-12">
                                    <form name="loginForm" class="loginForm" action="#" method="POST">
                                        <div class="form-group">
                                            <input type="email" class="form-control email" name="username" placeholder="Email address">
                                        </div>
                                        <div class="form-group">
                                            <div class="pwdMask">
                                                <input type="password" class="form-control password" name="password" placeholder="Password">
                                                <span class="fa fa-eye-slash pwd-toggle"></span>
                                            </div>
                                        </div>
                                        <!-- start remember-row -->
                                        <div class="row remember-row">
                                            <div class="col-xs-6 col-sm-6">
                                                <label class="checkbox text-left">
                                                    <input type="checkbox" value="remember-me">
<<<<<<< HEAD
                                                    <span class="label-text">Ghi nhớ tôi</span>
=======
                                                    <span class="label-text">Remember me</span>
>>>>>>> origin/HKa
                                                </label>
                                            </div>
                                            <div class="col-xs-6 col-sm-6">
                                                <p class="forgotPwd">
<<<<<<< HEAD
                                                    <a class="lnk-toggler" data-panel=".panel-forgot" href="#">Quên mật khẩu?</a>
=======
                                                    <a class="lnk-toggler" data-panel=".panel-forgot" href="#">Forgot password?</a>
>>>>>>> origin/HKa
                                                </p>
                                            </div>
                                        </div>
                                        <!-- ./remember-row -->
                                        <div class="form-group">
<<<<<<< HEAD
                                            <button class="btn btn-lg btn-primary btn-block" type="submit">Đăng nhập với email</button>
=======
                                            <button class="btn btn-lg btn-primary btn-block" type="submit">Login with email</button>
>>>>>>> origin/HKa
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <!-- ./panel-login -->
                        <!-- panel-signup start -->
                        <div class="authfy-panel panel-signup text-center">
                            <div class="row">
                                <div class="col-xs-12 col-sm-12">
                                    <div class="authfy-heading">
<<<<<<< HEAD
                                        <h3 class="auth-title">Đăng ký miễn phí!</h3>
=======
                                        <h3 class="auth-title">Sign up for free!</h3>
>>>>>>> origin/HKa
                                    </div>
                                    <form name="signupForm" class="signupForm" action="#" method="POST">
                                        <div class="form-group">
                                            <input type="email" class="form-control" name="username" placeholder="Email address">
                                        </div>
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="fullname" placeholder="Full name">
                                        </div>
                                        <div class="form-group">
                                            <div class="pwdMask">
                                                <input type="password" class="form-control" name="password" placeholder="Password">
                                                <span class="fa fa-eye-slash pwd-toggle"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
<<<<<<< HEAD
                                            <p class="term-policy text-muted small">Tôi đồng ý với <a href="https://policies.google.com/privacy?hl=vi">chính sách bảo mật</a> và <a href="https://policies.google.com/terms?hl=vi">điều khoản dịch vụ</a>.</p>
                                        </div>
                                        <div class="form-group">
                                            <button class="btn btn-lg btn-primary btn-block" type="submit">Đăng ký với email</button>
                                        </div>
                                    </form>
                                    <a class="lnk-toggler" data-panel=".panel-login" href="#">Đã có tài khoản?</a>
=======
                                            <p class="term-policy text-muted small">I agree to the <a href="#">privacy policy</a> and <a href="#">terms of service</a>.</p>
                                        </div>
                                        <div class="form-group">
                                            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up with email</button>
                                        </div>
                                    </form>
                                    <a class="lnk-toggler" data-panel=".panel-login" href="#">Already have an account?</a>
>>>>>>> origin/HKa
                                </div>
                            </div>
                        </div>
                        <!-- ./panel-signup -->
                        <!-- panel-forget start -->
                        <div class="authfy-panel panel-forgot">
                            <div class="row">
                                <div class="col-xs-12 col-sm-12">
                                    <div class="authfy-heading">
<<<<<<< HEAD
                                        <h3 class="auth-title">Khôi phục mật khẩu của bạn</h3>
                                        <p>Điền địa chỉ email của bạn bên dưới và chúng tôi sẽ gửi cho bạn một email kèm theo hướng dẫn thêm.</p>
=======
                                        <h3 class="auth-title">Recover your password</h3>
                                        <p>Fill in your e-mail address below and we will send you an email with further instructions.</p>
>>>>>>> origin/HKa
                                    </div>
                                    <form name="forgetForm" class="forgetForm" action="#" method="POST">
                                        <div class="form-group">
                                            <input type="email" class="form-control" name="username" placeholder="Email address">
                                        </div>
                                        <div class="form-group">
<<<<<<< HEAD
                                            <button class="btn btn-lg btn-primary btn-block" type="submit">Khôi phục mật khẩu của bạn</button>
                                        </div>
                                        <div class="form-group">
                                            <a class="lnk-toggler" data-panel=".panel-login" href="#">Đã có tài khoản?</a>
                                        </div>
                                        <div class="form-group">
                                            <a class="lnk-toggler" data-panel=".panel-signup" href="#">Chưa có tài khoản?</a>
=======
                                            <button class="btn btn-lg btn-primary btn-block" type="submit">Recover your password</button>
                                        </div>
                                        <div class="form-group">
                                            <a class="lnk-toggler" data-panel=".panel-login" href="#">Already have an account?</a>
                                        </div>
                                        <div class="form-group">
                                            <a class="lnk-toggler" data-panel=".panel-signup" href="#">Don’t have an account?</a>
>>>>>>> origin/HKa
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <!-- ./panel-forgot -->
                    </div>
                    <!-- ./authfy-login -->
                </div>
            </div>
        </div>
        <!-- ./row -->
    </div>
    <!-- ./container -->   

</body>
<<<<<<< HEAD
</html>
=======

</html>
>>>>>>> origin/HKa
