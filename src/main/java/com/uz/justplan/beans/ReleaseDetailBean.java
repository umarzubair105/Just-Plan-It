package com.uz.justplan.beans;

import com.uz.justplan.beans.cal.ResourceCapInRelease;
import com.uz.justplan.beans.response.EpicBean;
import com.uz.justplan.plan.Release;

import java.util.List;

public class ReleaseDetailBean {
    private Release release;
    private List<ResourceCapInRelease> resourceCaps;
    private List<EpicBean> epics;

    public Release getRelease() {
        return release;
    }

    public void setRelease(Release release) {
        this.release = release;
    }

    public List<ResourceCapInRelease> getResourceCaps() {
        return resourceCaps;
    }

    public void setResourceCaps(List<ResourceCapInRelease> resourceCaps) {
        this.resourceCaps = resourceCaps;
    }

    public List<EpicBean> getEpics() {
        return epics;
    }

    public void setEpics(List<EpicBean> epics) {
        this.epics = epics;
    }
}
