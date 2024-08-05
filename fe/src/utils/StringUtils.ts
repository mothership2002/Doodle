class StringUtils {
    /**
     * 문자열이 특정 서브스트링을 포함하는지 확인합니다.
     * @param str - 검사할 문자열
     * @param substring - 포함되어 있는지 확인할 서브스트링
     * @returns 서브스트링이 포함되어 있으면 true, 그렇지 않으면 false
     */
    static contains(str: string, substring: string): boolean {
        return str.includes(substring);
    }

    /**
     * 문자열을 대문자로 변환합니다.
     * @param str - 변환할 문자열
     * @returns 대문자로 변환된 문자열
     */
    static toUpperCase(str: string): string {
        return str.toUpperCase();
    }

    /**
     * 문자열을 소문자로 변환합니다.
     * @param str - 변환할 문자열
     * @returns 소문자로 변환된 문자열
     */
    static toLowerCase(str: string): string {
        return str.toLowerCase();
    }

    /**
     * 문자열의 좌우 공백을 제거합니다.
     * @param str - 변환할 문자열
     * @returns 좌우 공백이 제거된 문자열
     */
    static trim(str: string): string {
        return str.trim();
    }

    /**
     * 문자열을 특정 구분자로 분할합니다.
     * @param str - 분할할 문자열
     * @param separator - 문자열을 분할할 구분자
     * @returns 분할된 문자열 배열
     */
    static split(str: string, separator: string): string[] {
        return str.split(separator);
    }

    /**
     * 문자열 배열을 특정 구분자로 결합합니다.
     * @param strings - 결합할 문자열 배열
     * @param separator - 문자열을 결합할 구분자
     * @returns 결합된 문자열
     */
    static join(strings: string[], separator: string): string {
        return strings.join(separator);
    }

    /**
     * 문자열이 공백을 제외한 실제 값을 포함하는지 확인합니다.
     * @param str - 검사할 문자열
     * @returns 문자열에 공백을 제외한 실제 값이 있으면 true, 그렇지 않으면 false
     */
    static hasText(str: string | null): boolean {
        if (str === null) {
            return false;
        }
        return str.trim().length > 0;
    }
}

export default StringUtils;