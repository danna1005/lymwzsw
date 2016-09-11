package com.thiscc.xtsz.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.thiscc.tools.common.BizFactory;
import com.thiscc.tools.common.RandomValidateCode;
import com.thiscc.xtsz.biz.LoginInfoBiz;

/**
 * 
 * @author keys
 * 
 */
public class LoginAction extends DispatchAction {

	//
	private LoginInfoBiz logininfobiz = (LoginInfoBiz) BizFactory
			.getBean("loginInfoBiz");

	/**
	 * 把用户信息存入session中
	 * 
	 * @param tid
	 */
	public void SaveLoginInfo(List<Map<String, Object>> list,
			HttpSession session) {
		try {
			session.setAttribute("userid", list.get(0).get("id").toString());
			session.setAttribute("userno", list.get(0).get("userno").toString());
			session.setAttribute("username", list.get(0).get("username")
					.toString());
			session.setAttribute("userpass", list.get(0).get("userpass")
					.toString());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward LoginCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		String param = request.getParameter("param");
		JSONObject jsonparam = JSONObject.fromObject(param);
		String verifyCode = jsonparam.get("verifyCode").toString();

		if (!verifyCode.toUpperCase().equals(session.getAttribute("LOGINYZM"))) {
			writer.write("2");
			return ((ActionForward) null);
		} else {
			try {
				List<Map<String, Object>> list = logininfobiz.LoginCheck(param);
				if (list.size() > 0) {
					SaveLoginInfo(list, session);// 把用户信息保存到session中
					writer.write("1");
				} else {
					writer.write("0");
				}
			} catch (Exception e) {
				writer.write("3");
			}
			return ((ActionForward) null);
		}
	}

	public ActionForward getRanValidateCode(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
		response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		RandomValidateCode randomValidateCode = new RandomValidateCode();
		try {
			randomValidateCode.getRandcode(request, response);// 输出图片方法
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return ((ActionForward) null);
	}

	public ActionForward getMainTree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userid = "";
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		try {
			userid = session.getAttribute("userid").toString();
		} catch (Exception e) {
			userid = "";
		}
		if (!userid.equals("0") && !userid.equals("") && userid != null) {
			String tree = logininfobiz.getMainTree(userid);
			writer.write("var arr = " + tree);
		} else {
			writer.write("<script>alert('登录失败!');document.location.href='login.html';</script>");
		}
		return ((ActionForward) null);
	}

	public ActionForward GetUserInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		JSONObject user = new JSONObject();
		try {
			user.put("username", session.getAttribute("username"));
			user.put("userid", session.getAttribute("userid"));
			user.put("userno", session.getAttribute("userno"));
			user.put("ccno", session.getAttribute("ccno"));
		} catch (Exception e) {
		}
		writer.write(user.toString());
		return ((ActionForward) null);
	}
	

	public ActionForward ExitAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		session.invalidate();
		writer.write("<script>document.location.href='login.html';</script>");
		return ((ActionForward) null);
	}

	/**
	 * 手机登录验证
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mobileLoginCheck(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String token = request.getParameter("token");
		JSONObject param = new JSONObject();
		JSONObject result = new JSONObject();
		param.put("username", username);
		param.put("method", 0);
		param.put("password", password);
		param.put("token", token);
		try {
			List<Map<String, Object>> list = logininfobiz.LoginCheck(param);
			if (list.size() > 0) {
				SaveLoginInfo(list, session);// 把用户信息保存到session中
				logininfobiz.setToken(param);
				result.put("state", "1");
				result.put("userid", list.get(0).get("id").toString());
				result.put("username", list.get(0).get("username").toString());

			} else {
				result.put("state", "0");
			}
		} catch (Exception e) {
			result.put("state", "3");
		}
		writer.write(result.toString());
		return ((ActionForward) null);

	}
	/**
	 * 判断是否登录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward isLogin(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		boolean bool = false;
		Object userid = session.getAttribute("userid");
		try {
			if(userid!=null&&!userid.equals("")){
				bool = true;
			}
		} catch (Exception e) {
		}
		writer.write(String.valueOf(bool));
		return ((ActionForward) null);

	}
}