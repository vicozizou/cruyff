package com.aureabox.webcore.web.bean;

import java.util.List;

public interface BreadCrumbBean {
	List<BreadCrumb> getBreadCrumbs(String url);

	BreadCrumb getRootBreadCrumb();

	boolean isUseRootWhenUnfound();

	public class BreadCrumb {
		private String labelKey;
		private String url;

		public BreadCrumb(String labelKey, String url) {
			this.labelKey = labelKey;
			this.url = url;
		}

		public String getLabelKey() {
			return labelKey;
		}

		public String getUrl() {
			return url;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((labelKey == null) ? 0 : labelKey.hashCode());
			result = prime * result + ((url == null) ? 0 : url.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			BreadCrumb other = (BreadCrumb) obj;
			if (labelKey == null) {
				if (other.labelKey != null)
					return false;
			} else if (!labelKey.equals(other.labelKey))
				return false;
			if (url == null) {
				if (other.url != null)
					return false;
			} else if (!url.equals(other.url))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "BreadCrumb [labelKey=" + labelKey + ", url=" + url + "]";
		}
	}
}
