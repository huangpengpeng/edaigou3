package com.edaigou3.view._2;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.springframework.stereotype.Component;

import com.common.jdbc.page.Pagination;
import com.edaigou3.entity.ItemFilters;
import com.edaigou3.manager.ItemFiltersMng;
import com.edaigou3.view._0.TableView_0;
import com.edaigou3.view.base.IMainView.MessageBox2;
import com.edaigou3.view.base.IMainView.NewInstance;
import com.edaigou3.view.base.ISearchView;

@Component
public class SearchView_2 implements ISearchView {

	private Text nick;
	private Text pNumIid;
	private Button save;

	public void createContents(Shell shell) {

	}

	public void createContents(Composite composite) {
		Label lblNewLabel_31 = new Label(composite, SWT.NONE);
		lblNewLabel_31.setBounds(10, 10, 54, 12);
		lblNewLabel_31.setText("淘宝店铺");

		nick = new Text(composite, SWT.BORDER);
		nick.setBounds(70, 7, 86, 20);

		Label lblNewLabel_32 = new Label(composite, SWT.NONE);
		lblNewLabel_32.setBounds(175, 7, 54, 12);
		lblNewLabel_32.setText("掌柜昵称");

		pNumIid = new Text(composite, SWT.BORDER);
		pNumIid.setBounds(238, 7, 97, 18);

		save = new Button(composite, SWT.NONE);
		save.setBounds(1024, 5, 54, 22);
		save.setText("保存");
	}

	public void createListenter() {
		save.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				String nickValue = nick.getText();
				String pNumIidValue = pNumIid.getText();
				if (StringUtils.isBlank(nickValue)
						&& StringUtils.isBlank(pNumIidValue)) {
					MessageBox2.showErrorMsg("昵称和编号必须填写一项");
					return;
				}
				NewInstance.get(ItemFiltersMng.class).add(
						StringUtils.isBlank(pNumIidValue) ? null : Long
								.valueOf(pNumIidValue), nickValue);

				query(1);
				clearText();
			}
		});
	}

	public void preHandle() {

	}

	public void fullContents(Object... values) {

	}

	public Pagination query(Integer pageNo) {
		List<ItemFilters> itemFilters = NewInstance.get(ItemFiltersMng.class)
				.query();
		NewInstance.get(TableView_2.class).fullContents(itemFilters);
		return null;
	}

	public void setValue(String name, String view) {
	}

	public void clearText() {
		nick.setText("");
		pNumIid.setText("");
	}
}
