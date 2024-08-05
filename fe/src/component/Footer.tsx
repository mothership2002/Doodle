import {useEffect} from "react";
import useCssUtil from "../hook/useCssUtil";
import styles from "./css/Footer.module.css"

const Footer = () => {
    const getStyle = useCssUtil(styles);

    // mount
    useEffect(() => {

    }, [])

    return (
        <footer className={getStyle('footer')}>
            <p>
                &copy; Mothership's sample. All rights reserved.
                {/*<a href="https://example.com">Privacy Policy</a>*/}
            </p>
        </footer>
    );
}

export default Footer;