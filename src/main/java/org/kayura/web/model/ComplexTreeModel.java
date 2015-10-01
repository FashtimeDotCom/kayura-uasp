package org.kayura.web.model;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by jf on 15/2/25.
 */
public abstract class ComplexTreeModel<T> extends AbstractTreeModel {

	private static final long serialVersionUID = -6362312364082761241L;
	private Class<?> clazz;
	private Map<String, TreeNode> leafNode = new HashMap<String, TreeNode>();
	private String linkedField;

	@SuppressWarnings("unchecked")
	public ComplexTreeModel() {
		clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public abstract TreeNode model2Node(Object t);

	public ComplexTreeModel<?> link(Class<?> clazz, String field) throws Exception {
		this.clazz = clazz;
		this.linkedField = field;
		return this;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public ComplexTreeModel<?> generate(String idField, String parentField) throws Exception {
		List<?> data = load(getClazz());
		Map<String, TreeNode> nodeMap = new TreeMap<String, TreeNode>();
		Map<String, TreeNode> tempLeaf = new TreeMap<String, TreeNode>();
		for (int i = 0, len = data.size(); i < len; i++) {
			TreeNode node = model2Node(data.get(i));
			nodeMap.put(node.getId(), node);
			tempLeaf.put(node.getId(), node);
		}
		for (int i = 0, len = data.size(); i < len; i++) {
			Object t = data.get(i);
			Field idF = t.getClass().getDeclaredField(idField);
			Field parentF = t.getClass().getDeclaredField(parentField);
			idF.setAccessible(true);
			parentF.setAccessible(true);
			String id = idF.get(t).toString();
			String parent = parentF.get(t) == null ? null : parentF.get(t).toString();
			TreeNode meNode = nodeMap.get(id);
			if (parent != null && nodeMap.get(parent) != null) {
				TreeNode parentNode = nodeMap.get(parent);
				parentNode.getChildren().add(meNode);
				parentNode.setState("closed");
			} else {
				if (this.linkedField != null) {
					Field linkedF = t.getClass().getDeclaredField(linkedField);
					linkedF.setAccessible(true);
					String linkedV = linkedF.get(t) == null ? null : linkedF.get(t).toString();
					TreeNode linkedNode = leafNode.get(linkedV);
					if (linkedNode != null) {
						linkedNode.getChildren().add(meNode);
						linkedNode.setState("closed");
					} else {
						this.add(meNode);
					}
				} else {
					this.add(meNode);
				}
			}
		}
		leafNode = tempLeaf;
		wrapRoot();
		return this;
	}

	public ComplexTreeModel<?> generate(String codeField, int deep) throws Exception {

		List<?> data = load(getClazz());
		Map<String, TreeNode> nodeMap = new HashMap<String, TreeNode>();
		Map<String, TreeNode> tempLeaf = new TreeMap<String, TreeNode>();
		for (int i = 0, len = data.size(); i < len; i++) {
			Object t = data.get(i);
			Field codeF = t.getClass().getDeclaredField(codeField);
			codeF.setAccessible(true);
			Object codeO = codeF.get(t);
			if (codeO == null || codeO.toString().length() < deep) {
				continue;
			}
			String code = codeO.toString();
			TreeNode node = model2Node(t);
			nodeMap.put(code, node);
			tempLeaf.put(node.getId(), node);
		}
		for (int i = 0, len = data.size(); i < len; i++) {
			Object t = data.get(i);
			Field codeF = t.getClass().getDeclaredField(codeField);
			codeF.setAccessible(true);
			Object codeO = codeF.get(t);
			if (codeO == null || codeO.toString().length() < deep) {
				continue;
			}
			String code = codeO.toString();
			String parentCode = code.substring(0, code.length() - deep);
			TreeNode meNode = nodeMap.get(code);
			if (parentCode.length() == 0 || nodeMap.get(parentCode) == null) {
				if (this.linkedField != null) {
					Field linkedF = t.getClass().getDeclaredField(linkedField);
					linkedF.setAccessible(true);
					String linkedV = linkedF.get(t) == null ? null : linkedF.get(t).toString();
					TreeNode linkedNode = leafNode.get(linkedV);
					if (linkedNode != null) {
						linkedNode.getChildren().add(meNode);
						linkedNode.setState("closed");
					} else {
						this.add(meNode);
					}
				} else {
					this.add(meNode);
				}
			} else {
				TreeNode parentNode = nodeMap.get(parentCode);
				if (parentNode != null) {
					parentNode.getChildren().add(meNode);
					parentNode.setState("closed");
				}
			}

		}
		leafNode = tempLeaf;
		wrapRoot();
		return this;
	}

	public void generate() throws Exception {
		List<?> data = load(getClazz());
		for (int i = 0, len = data.size(); i < len; i++) {
			Object t = data.get(i);
			TreeNode meNode = model2Node(t);
			if (this.linkedField != null) {
				Field linkedF = t.getClass().getDeclaredField(linkedField);
				linkedF.setAccessible(true);
				String linkedV = linkedF.get(t) == null ? null : linkedF.get(t).toString();
				TreeNode linkedNode = leafNode.get(linkedV);
				if (linkedNode != null) {
					linkedNode.getChildren().add(meNode);
					linkedNode.setState("closed");
				} else {
					this.add(meNode);
				}
			} else {
				this.add(meNode);
			}
		}
	}

	protected abstract List<?> load(Class<?> clazz) throws Exception;
}
