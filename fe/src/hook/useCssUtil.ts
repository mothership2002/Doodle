import {useEffect, useState} from "react";
import CssUtil from "../utils/CssUtil";

const useCssUtil = (styles: object) => {

    const [getStyle, setGetStyle] = useState<(className: string) => string>(() => () => '');

    useEffect(() => {
        const cssUtil = new CssUtil(styles);
        setGetStyle(() => (className: string) => cssUtil.getStyle(className));
    }, [styles]);

    return getStyle;
}

export default useCssUtil;