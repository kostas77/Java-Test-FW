package com.elsevier.subsys.framework.controls.elements;

import com.elsevier.subsys.framework.controls.api.ImplementedBy;
import com.elsevier.subsys.framework.controls.internals.Control;

@ImplementedBy(TextBase.class)
public interface Text extends Control {

    String GetTextValue();

}
