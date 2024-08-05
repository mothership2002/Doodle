class CssUtil {
    private readonly styles: any;
    constructor(styles: object) {
        this.styles = styles
    }

    getStyle(className: string) {
        return this.styles[className] || '';
    }
}

export default CssUtil;