package com.elsevier.bts.regional.ecom.framework.controls.elements;

import com.elsevier.bts.regional.ecom.framework.controls.api.ImplementedBy;
import com.elsevier.bts.regional.ecom.framework.controls.internals.Control;

@ImplementedBy(TextBase.class)
public interface Text extends Control {

    String GetTextValue();

}
