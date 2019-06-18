package nl.ckramer.webshop.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@ApplicationScoped
@ManagedBean(name = "indexBean")
public class IndexBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<String> images;
	
	@PostConstruct
	public void init(){
		images = new ArrayList<String>();
        for (int i = 1; i <= 13; i++) {
            images.add("image" + i + ".jpg");
        }
	}

}
