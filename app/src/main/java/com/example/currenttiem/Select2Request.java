package com.example.currenttiem;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Select2Request extends StringRequest {

    final static private String URL = "http://leedh2717.dothome.co.kr/select2Test.php";
    private Map<String, String> map;

    public Select2Request(Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}
