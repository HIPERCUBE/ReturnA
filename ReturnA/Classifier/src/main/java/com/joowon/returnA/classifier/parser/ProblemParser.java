package com.joowon.returnA.classifier.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Copyright (c) 4/3/16 Joowon Ryoo
 * <p>
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * <p>
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
public class ProblemParser extends Parser {
    public static void main(String[] args) throws InterruptedException {
        String text = "/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/bin/java -Didea.launcher.port=7532 \"-Didea.launcher.bin.path=/Applications/IntelliJ IDEA 15.app/Contents/bin\" -Dfile.encoding=UTF-8 -classpath \"/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/deploy.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/cldrdata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/dnsns.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/jaccess.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/jfxrt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/localedata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/nashorn.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/sunec.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/zipfs.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/javaws.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/jfr.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/jfxswt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/management-agent.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/plugin.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/lib/ant-javafx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/lib/dt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/lib/javafx-mx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/lib/jconsole.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/lib/packager.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/lib/sa-jdi.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/lib/tools.jar:/Users/Joowon/Documents/Github/ReturnA/ReturnA/Classifier/build/classes/main:/Users/Joowon/Documents/Github/ReturnA/ReturnA/Classifier/build/resources/main:/Users/Joowon/Documents/Github/ReturnA/ReturnA/Classifier/libs/pdfbox-app-2.0.0.jar:/Users/Joowon/Documents/Github/ReturnA/ReturnA/Classifier/libs/opencv-300.jar:/Applications/IntelliJ IDEA 15.app/Contents/lib/idea_rt.jar\" com.intellij.rt.execution.application.AppMain com.joowon.returnA.classifier.Classifier\n" +
                "Apr 03, 2016 4:19:52 PM org.apache.pdfbox.pdmodel.font.PDCIDFontType2 <init>\n" +
                "WARNING: Using fallback font ArialUnicodeMS for CID-keyed TrueType font HYGoThic,Bold\n" +
                "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/ReturnA/image_1.png [1/8]\n" +
                "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/ReturnA/image_2.png [2/8]\n" +
                "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/ReturnA/image_3.png [3/8]\n" +
                "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/ReturnA/image_4.png [4/8]\n" +
                "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/ReturnA/image_5.png [5/8]\n" +
                "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/ReturnA/image_6.png [6/8]\n" +
                "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/ReturnA/image_7.png [7/8]\n" +
                "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/ReturnA/image_8.png [8/8]\n" +
                "2015학년도 대학수학능력시험 문제지\n" +
                "[  1번부터 17번까지는 듣고 답하는 문제입니다. 1번부터 \n" +
                "15번까지는 한 번만 들려주고, 16번부터 17번까지는 두 번 \n" +
                "들려줍니다. 방송을 잘 듣고 답을 하시기 바랍니다.\n" +
                "1. 대화를 듣고, 남자의 마지막 말에 대한 여자의 응답으로 가장 \n" +
                "적절한 것을 고르시오.\n" +
                "① Sorry. I already ate dinner.\n" +
                "② No. I’ve never met him before.\n" +
                "③ Sure. Thanks for the invitation.\n" +
                "④ Yes. I bought a box of chocolates.\n" +
                "⑤ Good. I hate working on weekends.\n" +
                "2. 대화를 듣고, 여자의 마지막 말에 대한 남자의 응답으로 가장 \n" +
                "적절한 것을 고르시오.\n" +
                "① Okay, let me take a look.\n" +
                "② I agree. The timing isn’t good.\n" +
                "③ Wow, this winter is really cold.\n" +
                "④ You’re welcome. It’s my pleasure. \n" +
                "⑤ No problem. I’ll pick up your daughter.\n" +
                "3. 다음을 듣고, 남자가 하는 말의 목적으로 가장 적절한 것을 \n" +
                "고르시오.\n" +
                "① 교복 물려주기를 권장하려고\n" +
                "② 기부 행사 참여를 독려하려고\n" +
                "③ 생활 용품 절약을 장려하려고\n" +
                "④ 학교 식당 공사를 안내하려고\n" +
                "⑤ 학부모 간담회 일정을 공지하려고\n" +
                "4. 대화를 듣고, 두 사람이 하는 말의 주제로 가장 적절한 것을 \n" +
                "고르시오.\n" +
                "① 충분한 낮잠의 필요성\n" +
                "② 규칙적인 운동의 중요성\n" +
                "③ 미디어 기기가 학습에 미치는 영향\n" +
                "④ 휴대전화와 업무 효율성의 관계\n" +
                "⑤ 수면 문제를 해결하는 방법\n" +
                "5. 대화를 듣고, 두 사람의 관계를 가장 잘 나타낸 것을 고르시오.\n" +
                "① 연출가－ 배우 ② 작곡가－ 가수\n" +
                "③ 사회자－ 요리사 ④ 식당 지배인－ 고객\n" +
                "⑤ 농장 주인－ 도매업자\n" +
                "][6. 대화를 듣고, 그림에서 대화의 내용과 일치하지 않는 것을 \n" +
                "고르시오.\n" +
                "7. 대화를 듣고, 남자가 여자에게 부탁한 일로 가장 적절한 것을 \n" +
                "고르시오.\n" +
                "① to register as a presidential candidate\n" +
                "② to speak out against school violence\n" +
                "③ to cancel the student meeting\n" +
                "④ to take the adviser position\n" +
                "⑤ to remove election posters\n" +
                "8. 대화를 듣고, 남자가 야구 경기를 보러 갈 수 없는 이유를 고르시오.\n" +
                "① 독감에 걸려서\n" +
                "② 비행기를 놓쳐서\n" +
                "③ 동생을 돌봐야 해서\n" +
                "④ 회의에 참석해야 해서\n" +
                "⑤ 입장권을 구할 수 없어서\n" +
                "9. 대화를 듣고, 여자가 지불할 금액을 고르시오. [3점]\n" +
                "① $9 ② $16 ③ $18 ④ $20 ⑤ $27\n" +
                "10. 대화를 듣고, 미용실에 관해 두 사람이 언급하지 않은 것을 \n" +
                "고르시오.\n" +
                "① 가게 이름 ② 위치 ③ 남자 이발 비용\n" +
                "④ 영업시간 ⑤ 미용사 이름\n" +
                "11. science essay contest에 관한 다음 내용을 듣고, 일치하지 \n" +
                "않는 것을 고르시오.\n" +
                "① 고등학생이 참가할 수 있다.\n" +
                "② 에세이 주제가 두 가지이다.\n" +
                "③ 에세이 분량에 제한이 있다.\n" +
                "④ 온라인으로만 에세이 제출을 허용한다.\n" +
                "⑤ 참가자 전원에게 후원 기관 견학 기회를 준다.\n" +
                "][12. 다음 표를 보면서 대화를 듣고, 두 사람이 선택할 패키지 \n" +
                "상품을 고르시오.\n" +
                "Attraction Packages at Grandlife Amusement Park\n" +
                "①\n" +
                "②  \n" +
                "③\n" +
                "④\n" +
                "⑤\n" +
                "13. 대화를 듣고, 여자의 마지막 말에 대한 남자의 응답으로 가장 \n" +
                "적절한 것을 고르시오. [3점]\n" +
                "Man:\n" +
                "① Set a time limit when making a presentation.\n" +
                "② The more knowledge, the better the interview.\n" +
                "③ The company buys your creativity, not your image.\n" +
                "④ Interviewing is no more than expressing yourself.\n" +
                "⑤ Too much confidence in your vision doesn’t help.\n" +
                "14. 대화를 듣고, 남자의 마지막 말에 대한 여자의 응답으로 가장 \n" +
                "적절한 것을 고르시오. \n" +
                "Woman:\n" +
                "① Let’s go to the history museum now.\n" +
                "② I can memorize your phone number.\n" +
                "③ You prepared a lot for the event.\n" +
                "④ You should share it with others.\n" +
                "⑤ I’ll write it down for you then.\n" +
                "15. 다음 상황 설명을 듣고, Peter가 Sandra에게 할 말로 가장 \n" +
                "적절한 것을 고르시오. [3점]\n" +
                "Peter: Sandra, \n" +
                "① I think you need to take a break for a while.\n" +
                "② I recommend you return your book soon.\n" +
                "③ I’d suggest changing where you study.\n" +
                "④ you’d better review what you learned.\n" +
                "⑤ you should’ve slept more last night.\n" +
                "[16～17] 다음을 듣고, 물음에 답하시오.\n" +
                "16. 여자가 하는 말의 주제로 가장 적절한 것은?\n" +
                "① ways of making peace with family members\n" +
                "② causes of conflict within a family\n" +
                "③ various merits of having hobbies\n" +
                "④ difficulties of managing a relationship\n" +
                "⑤ consequences of communication problems\n" +
                "17. 언급된 물건이 아닌 것은?\n" +
                "① a letter ② a picture ③ cookies\n" +
                "④ earrings ⑤ flowers\n" +
                "  이제 듣기․말하기 문제가 끝났습니다. 18번부터는 문제지의 \n" +
                "지시에 따라 답을 하시기 바랍니다.\n" +
                "][18. 다음 글의 요지로 가장 적절한 것은?\n" +
                "One difference between winners and losers is how they handle \n" +
                "losing. Even for the best companies and most accomplished \n" +
                "professionals, long track records of success are punctuated by \n" +
                "slips, slides, and mini-turnarounds. Even the team that wins \n" +
                "the game might make mistakes and lag behind for part of it. \n" +
                "That’s why the ability to recover quickly is so important. \n" +
                "Troubles are ubiquitous. Surprises can fall from the sky like \n" +
                "volcanic ash and appear to change everything. That’s why one \n" +
                "prominent scholar said, “Anything can look like a failure in \n" +
                "the middle.” Thus, a key factor in high achievement is \n" +
                "bouncing back from the low points.\n" +
                "① 실패를 빨리 극복하는 것이 성공의 열쇠이다.\n" +
                "② 폭넓은 인간 관계는 성공의 필수 요소이다.\n" +
                "③ 합리적 소비는 필요와 욕구의 구분에서 비롯된다.\n" +
                "④ 위기 관리에는 전문가의 조언이 필요하다.\n" +
                "⑤ 경영의 전문화는 일류 기업의 조건이다.\n" +
                "19. 다음 글에 드러난 ‘I’의 심경으로 가장 적절한 것은? \n" +
                "As I walked to the train station, I felt the warm sun on my \n" +
                "back. I caught my train on time. It arrived at my destination ten \n" +
                "minutes early, which was perfect, as I was due to present my \n" +
                "new idea to the company at 10 a.m. The presentation went better \n" +
                "than expected, and my manager seemed particularly pleased. \n" +
                "Later that day, my manager called me into her office. She smiled \n" +
                "at me and said, “James, you’ve been with us for six years now. \n" +
                "How would you feel if I were to offer you the Sales Director \n" +
                "position in London?” Sales Director in London! Wow! This was \n" +
                "a dream come true for me. I couldn’t believe what I had just heard!\n" +
                "① indifferent ② jealous ③ outraged\n" +
                "④ confused ⑤ joyful\n" +
                "[20～21] 다음 글의 주제로 가장 적절한 것을 고르시오.\n" +
                "20. Many disciplines are better learned by entering into the \n" +
                "doing than by mere abstract study. This is often the case with \n" +
                "the most abstract as well as the seemingly more practical \n" +
                "disciplines. For example, within the philosophical disciplines, \n" +
                "logic must be learned through the use of examples and actual \n" +
                "problem solving. Only after some time and struggle does the \n" +
                "student begin to develop the insights and intuitions that enable \n" +
                "him to see the centrality and relevance of this mode of \n" +
                "thinking. This learning by doing is essential in many of the \n" +
                "sciences. For instance, only after a good deal of observation do \n" +
                "the sparks in the bubble chamber become recognizable as the \n" +
                "specific movements of identifiable particles.\n" +
                "① history of science education\n" +
                "② limitations of learning strategies\n" +
                "③ importance of learning by doing\n" +
                "④ effects of intuition on scientific discoveries\n" +
                "⑤ difference between philosophy and science\n" +
                "][21. The most normal and competent child encounters what \n" +
                "seem like insurmountable problems in living. But by playing \n" +
                "them out, he may become able to cope with them in a \n" +
                "step-by-step process. He often does so in symbolic ways that \n" +
                "are hard for even him to understand, as he is reacting to inner \n" +
                "processes whose origin may be buried deep in his \n" +
                "unconscious. This may result in play that makes little sense to \n" +
                "us at the moment, since we do not know the purposes it \n" +
                "serves. When there is no immediate danger, it is usually best \n" +
                "to approve of the child’s play without interfering. Efforts to \n" +
                "assist him in his struggles, while well intentioned, may divert \n" +
                "him from seeking and eventually finding the solution that will \n" +
                "serve him best.\n" +
                "① children’s play as problem solving with minimal intervention\n" +
                "② beneficial influence of playing outdoors in childhood \n" +
                "③ necessity of intervening in disputes between siblings\n" +
                "④ dangers of playing violent games to mental health\n" +
                "⑤ parental roles in children’s physical development\n" +
                "[22～23] 다음 글의 제목으로 가장 적절한 것을 고르시오.\n" +
                "22. At some time in their lives, most people pause to reflect on \n" +
                "their own moral principles and on the practical implications of \n" +
                "those principles, and they sometimes think about what \n" +
                "principles people should have or which moral standards can \n" +
                "be best justified. When a person accepts a moral principle, \n" +
                "naturally the person believes the principle is important and \n" +
                "well justified. But there is more to moral principles than that. \n" +
                "When a principle is part of a person’s moral code, that person \n" +
                "is strongly motivated toward the conduct required by the \n" +
                "principle, and against behavior that conflicts with that \n" +
                "principle. The person will tend to feel guilty when his or her \n" +
                "own conduct violates that principle and to disapprove of \n" +
                "others whose behavior conflicts with it. Likewise, the person \n" +
                "will tend to hold in esteem those whose conduct shows an \n" +
                "abundance of the motivation required by the principle.\n" +
                "① Feeling Guilty? Check Your Self-Esteem First\n" +
                "② Do Not Let Your Moral Principles Change!\n" +
                "③ Moral Integrity: A Principle of Philosophy\n" +
                "④ How Do People Form Their Personalities?\n" +
                "⑤ Moral Principles: Guiding Our Conduct\n" +
                "][23. The key to successful risk taking is to understand that the \n" +
                "actions you’re taking should be the natural next step. One of \n" +
                "the mistakes we often make when confronting a risk situation \n" +
                "is our tendency to focus on the end result. Skiers who are \n" +
                "unsure of themselves often do this. They’ll go to the edge of a \n" +
                "difficult slope, look all the way down to the bottom, and \n" +
                "determine that the slope is too steep for them to try. The ones \n" +
                "that decide to make it change their focus by analyzing what \n" +
                "they need to do to master the first step, like getting through \n" +
                "the first mogul on the hill. Once they get there, they \n" +
                "concentrate on the next mogul, and over the course of the run, \n" +
                "they end up at the bottom of what others thought was an \n" +
                "impossible mountain.\n" +
                "* mogul: 모굴(스키의 활주 사면에 있는 단단한 눈 더미)\n" +
                "① Success Through Risk Avoidance\n" +
                "② Start with Ultimate Goals in Mind!\n" +
                "③ The Wonders of Committed Efforts\n" +
                "④ Focus on the Next Step, Not the Final Result\n" +
                "⑤ Separating the Possible from the Impossible\n" +
                "24. Georg Dionysius Ehret에 관한 다음 글의 내용과 일치하지 \n" +
                "않는 것은?\n" +
                "The 18th century is called the Golden Age of botanical \n" +
                "painting, and Georg Dionysius Ehret is often praised as the \n" +
                "greatest botanical artist of the time. Born in Heidelberg, \n" +
                "Germany, he was the son of a gardener who taught him much \n" +
                "about art and nature. As a young man, Ehret traveled around \n" +
                "Europe, largely on foot, observing plants and developing his \n" +
                "artistic skills. In Holland, he became acquainted with the \n" +
                "Swedish naturalist Carl Linnaeus. Through his collaborations with \n" +
                "Linnaeus and others, Ehret provided illustrations for a number \n" +
                "of significant horticultural publications. Ehret’s reputation for \n" +
                "scientific accuracy gained him many commissions from wealthy \n" +
                "patrons, particularly in England, where he eventually settled.\n" +
                "* horticultural: 원예(학)의\n" +
                "① 18세기의 가장 위대한 식물 화가로서 칭송받는다.\n" +
                "② 정원사의 아들이었다.\n" +
                "③ 젊은 시절 주로 마차로 유럽을 여행하였다. \n" +
                "④ 다수의 원예 출판물에 삽화를 제공하였다.\n" +
                "⑤ 영국에 정착하였다.\n" +
                "][25. 다음 도표의 내용과 일치하지 않는 것은?\n" +
                "The above graph shows the percentages of Americans aged \n" +
                "12-17 who posted certain types of personal information on \n" +
                "social media sites in 2006 and in 2012. ① The year 2012 saw \n" +
                "an overall percentage increase in each category of posted \n" +
                "personal information. ② In both years, the percentage of the \n" +
                "young Americans who posted photos of themselves was the \n" +
                "highest of all the categories. ③ In 2006, the percentage of \n" +
                "those who posted city or town names was higher than that of \n" +
                "those who posted school names. ④ Regarding posted email \n" +
                "addresses, the percentage of 2012 was three times higher than \n" +
                "that of 2006. ⑤ Compared to 2006, 2012 recorded an eighteen \n" +
                "percent increase in the category of cell phone numbers.\n" +
                "26. Short Film Festival에 관한 다음 안내문의 내용과 일치하는 것은?\n" +
                "SHORT FILM FESTIVAL\n" +
                "We will be hosting nine short films, which were written, \n" +
                "directed, acted and produced by students from the College \n" +
                "of Performing Arts & Film, Pamil University.\n" +
                "∙Date: Friday, November 21, 2014\n" +
                "∙Time: 7:00 pm－10:00 pm\n" +
                "∙Place: Pamil Auditorium, Pamil University\n" +
                "∙Price:\n" +
                "$10 (general admission)\n" +
                "$5 (discount for all university students with a valid ID)\n" +
                "- Tickets can be purchased from the student union\n" +
                "office from Monday, November 17, 2014.\n" +
                "- All tickets are non-refundable.\n" +
                "- FREE beverage included in ticket price\n" +
                "∙For more information, please call the student union\n" +
                "office at (343) 777-8338.\n" +
                "① 교수들이 제작한 영화가 상영된다.\n" +
                "② 오전에 세 시간 동안 진행된다.\n" +
                "③ 영화 전공 학생에게만 입장료를 할인해 준다. \n" +
                "④ 입장권은 환불이 가능하다.\n" +
                "⑤ 무료 음료가 입장료에 포함된다.\n" +
                "][27. After-School Program에 관한 다음 안내문의 내용과 일치하지  \n" +
                "않는 것은?\n" +
                "AFTER-SCHOOL PROGRAM \n" +
                "December 1, 2014－January 30, 2015\n" +
                "                    Are you looking for fun and exciting classes?\n" +
                "Come on down to the Green Hills Community Center to \n" +
                "check out our FREE program for local teens!\n" +
                "Classes\n" +
                "∙Art, Music, Taekwondo\n" +
                "∙Classes with fewer than 20 applicants will be canceled.\n" +
                "Time & Place\n" +
                "∙The program will run from Monday to Friday\n" +
                "(5:00 pm to 7:00 pm).\n" +
                "∙All classes will take place in the Simpson Building.\n" +
                "How to sign up\n" +
                "∙Registration forms must be sent by email to the address \n" +
                "below by 6:00 pm, November 28. Please download the \n" +
                "forms from our website at www.greenhills.org.\n" +
                " For additional information, please visit our website\n" +
                "or send an email to bill@greenhills.org.\n" +
                "① 지역의 십 대들을 위한 무료 프로그램이다.\n" +
                "② 신청자가 20명 미만인 수업은 취소된다.\n" +
                "③ 모든 수업은 Simpson Building에서 진행된다.\n" +
                "④ 등록 신청서는 직접 방문하여 제출해야 한다.\n" +
                "⑤ 추가 정보는 웹사이트나 이메일을 이용하면 된다.\n" +
                "28. 다음 글의 밑줄 친 부분 중, 어법상 틀린 것은? [3점]\n" +
                "During the early stages when the aquaculture industry was \n" +
                "rapidly expanding, mistakes were made and these were costly \n" +
                "both in terms of direct losses and in respect of the industry’s \n" +
                "image. High-density rearing led to outbreaks of infectious \n" +
                "diseases that in some cases ① devastated not just the caged \n" +
                "fish, but local wild fish populations too. The negative impact \n" +
                "on local wildlife inhabiting areas ② close to the fish farms \n" +
                "continues to be an ongoing public relations problem for the \n" +
                "industry. Furthermore, a general lack of knowledge and \n" +
                "insufficient care being taken when fish pens were initially \n" +
                "constructed ③ meaning that pollution from excess feed and \n" +
                "fish waste created huge barren underwater deserts. These were \n" +
                "costly lessons to learn, but now stricter regulations are in \n" +
                "place to ensure that fish pens are placed in sites ④ where there \n" +
                "is good water flow to remove fish waste. This, in addition to \n" +
                "other methods that decrease the overall amount of uneaten \n" +
                "food, ⑤ has helped aquaculture to clean up its act.\n" +
                "][29. 밑줄 친 부분이 가리키는 대상이 나머지 넷과 다른 것은?\n" +
                "Nancy was struggling to see the positive when ① her teen \n" +
                "daughter was experiencing a negative perspective on her life \n" +
                "and abilities. In her desire to parent intentionally, ② she went \n" +
                "into her daughter’s room and noted one positive accomplishment \n" +
                "she had observed. “I know you’ve been having a hard time \n" +
                "lately, and you aren’t feeling really good or positive about \n" +
                "your life. But you did a great job cleaning up your room \n" +
                "today, and ③ I know that must have been a big effort for you.” \n" +
                "The next day, to Nancy’s surprise, the teen girl seemed \n" +
                "somewhat cheerful. In passing, ④ she said, “Mom, thanks for \n" +
                "saying the positive thing about me yesterday. I was feeling so \n" +
                "down and couldn’t think of anything good about myself. After \n" +
                "⑤ you said that positive thing, it helped me see one good \n" +
                "quality in myself, and I’ve been holding onto those words.”\n" +
                "30. (A), (B), (C)의 각 네모 안에서 문맥에 맞는 낱말로 가장 \n" +
                "적절한 것은? [3점]\n" +
                "While the eye sees at the surface, the ear tends to penetrate \n" +
                "below the surface. Joachim-Ernst Berendt points out that the ear \n" +
                "is the only sense that (A) fuses / replaces an ability to measure \n" +
                "with an ability to judge. We can discern different colors, but \n" +
                "we can give a precise number to different sounds. Our eyes do \n" +
                "not let us perceive with this kind of (B) diversity / precision . An \n" +
                "unmusical person can recognize an octave and, perhaps once \n" +
                "instructed, a quality of tone, that is, a C or an F-sharp. Berendt \n" +
                "points out that there are few ‘acoustical illusions’― something \n" +
                "sounding like something that in fact it is not― while there are \n" +
                "many optical illusions. The ears do not lie. The sense of \n" +
                "hearing gives us a remarkable connection with the invisible, \n" +
                "underlying order of things. Through our ears we gain access to \n" +
                "vibration, which (C) underlies / undermines everything around \n" +
                "us. The sense of tone and music in another’s voice gives us an \n" +
                "enormous amount of information about that person, about her \n" +
                "stance toward life, about her intentions.\n" +
                "* acoustical: 청각의 \n" +
                "(A) (B) (C)\n" +
                "① fuses …… precision …… undermines\n" +
                "② replaces …… diversity …… underlies\n" +
                "③ fuses …… diversity …… undermines\n" +
                "④ replaces …… precision …… underlies\n" +
                "⑤ fuses …… precision …… underlies\n" +
                "][[31～33] 다음 빈칸에 들어갈 말로 가장 적절한 것을 고르시오.\n" +
                "31. The concept of humans doing multiple things at a time has \n" +
                "been studied by psychologists since the 1920s, but the term \n" +
                "“multitasking” didn’t exist until the 1960s. It was used to \n" +
                "describe computers, not people. Back then, ten megahertz was \n" +
                "so fast that a new word was needed to describe a computer’s \n" +
                "ability to quickly perform many tasks. In retrospect, they \n" +
                "probably made a poor choice, for the expression “multitasking” \n" +
                "is inherently deceptive. Multitasking is about multiple tasks \n" +
                "alternately sharing one resource (the CPU), but in time the \n" +
                "context was flipped and it became interpreted to mean multiple \n" +
                "tasks being done simultaneously by one resource (a person). It \n" +
                "was a clever turn of phrase that’s misleading, for even \n" +
                "computers can process only one piece of code at a time. When \n" +
                "they “multitask,” they switch back and forth, alternating their \n" +
                "attention until both tasks are done. The speed with which \n" +
                "computers tackle multiple tasks that \n" +
                "everything happens at the same time, so comparing computers \n" +
                "to humans can be confusing. [3점]\n" +
                "① expels the myth ② conceals the fact\n" +
                "③ feeds the illusion ④ proves the hypothesis\n" +
                "⑤ blurs the conviction\n" +
                "32. My friend was disappointed that scientific progress has not \n" +
                "cured the world’s ills by abolishing wars and starvation; that \n" +
                "gross human inequality is still widespread; that happiness is \n" +
                "not universal. My friend made a common mistake― a basic \n" +
                "misunderstanding in the nature of knowledge. Knowledge is \n" +
                "amoral― not immoral but morality neutral. It can be used for \n" +
                "any purpose, but many people assume it will be used to further \n" +
                "their favorite hopes for society― and this is the fundamental flaw. \n" +
                "Knowledge of the world is one thing; its uses create a separate \n" +
                "issue. To be disappointed that our progress in understanding \n" +
                "has not remedied the social ills of the world is a legitimate \n" +
                "view, but . \n" +
                "To argue that knowledge is not progressing because of the \n" +
                "African or Middle Eastern conflicts misses the point. There is \n" +
                "nothing inherent in knowledge that dictates any specific social \n" +
                "or moral application. [3점]\n" +
                "① to confuse this with the progress of knowledge is absurd\n" +
                "② to know the nature of knowledge is to practice its moral value\n" +
                "③ to remove social inequality is the inherent purpose of knowledge\n" +
                "④ to accumulate knowledge is to enhance its social application\n" +
                "⑤ to make science progress is to make it cure social ills\n" +
                "][33. According to a renowned French scholar, the growth in the \n" +
                "size and complexity of human populations was the driving \n" +
                "force in the evolution of science. Early, small communities had \n" +
                "to concentrate all their physical and mental effort on survival; \n" +
                "their thoughts were focused on food and religion. As \n" +
                "communities became larger, some people had time to reflect \n" +
                "and debate. They found that they could understand and predict \n" +
                "events better if they reduced passion and prejudice, replacing \n" +
                "these with observation and inference. But while a large \n" +
                "population may have been necessary, in itself it was not \n" +
                "sufficient for science to germinate. Some empires were big, \n" +
                "but the rigid social control required to hold an empire together \n" +
                "was not beneficial to science, just as it was not beneficial to \n" +
                "reason. The early nurturing and later flowering of science      \n" +
                "                                                                                 to support \n" +
                "original thought and freewheeling incentive. The rise in \n" +
                "commerce and the decline of authoritarian religion allowed \n" +
                "science to follow reason in seventeenth-century Europe. [3점]\n" +
                "* germinate: 싹트다, 발아하다\n" +
                "① prompted small communities to adopt harsh social norms\n" +
                "② resulted from passion and enthusiasm rather than inference\n" +
                "③ occurred in large communities with strict hierarchical structures\n" +
                "④ required a large and loosely structured, competitive community\n" +
                "⑤ were solely attributed to efforts of survival in a small community\n" +
                "34. 다음 글의 빈칸 (A), (B)에 들어갈 말로 가장 적절한 것은? \n" +
                "New media can be defined by four characteristics \n" +
                "simultaneously: they are media at the turn of the 20th and 21st \n" +
                "centuries which are both integrated and interactive and use \n" +
                "digital code and hypertext as technical means. It follows that \n" +
                "their most common alternative names are multimedia, \n" +
                "interactive media and digital media. By using this definition, \n" +
                "it is easy to identify media as old or new. (A) , \n" +
                "traditional television is integrated as it contains images, sound \n" +
                "and text, but it is not interactive or based on digital code. The \n" +
                "plain old telephone was interactive, but not integrated as it \n" +
                "only transmitted speech and sounds and it did not work with \n" +
                "digital code. In contrast, the new medium of interactive \n" +
                "television adds interactivity and digital code. (B) , \n" +
                "the new generations of mobile or fixed telephony are fully \n" +
                "digitalized and integrated as they add text, pictures or video \n" +
                "and they are connected to the Internet.\n" +
                "(A) (B)\n" +
                "① For example …… Additionally\n" +
                "② Nevertheless …… In other words\n" +
                "③ Therefore …… Additionally\n" +
                "④ For example …… In other words\n" +
                "⑤ Nevertheless …… Consequently\n" +
                "][35. 다음 글에서 전체 흐름과 관계 없는 문장은? \n" +
                "A currently popular attitude is to blame technology or \n" +
                "technologists for having brought on the environmental \n" +
                "problems we face today, and thus to try to slow technological \n" +
                "advance by blocking economic growth. We believe this view \n" +
                "to be thoroughly misguided. ① If technology produced \n" +
                "automobiles that pollute the air, it is because pollution was not \n" +
                "recognized as a problem which engineers had to consider in \n" +
                "their designs. ② Solar energy can be a practical alternative \n" +
                "energy source for us in the foreseeable future. ③ Obviously, \n" +
                "technology that produces pollution is generally cheaper, but \n" +
                "now that it has been decided that cleaner cars are wanted, less \n" +
                "polluting cars will be produced; cars which scarcely pollute at \n" +
                "   \n" +
                "all could even be made. ④ This last option, however, would \n" +
                "require several years and much investment. ⑤ Although \n" +
                "technology is responsive to the will of the people, it can \n" +
                "seldom respond instantaneously and is never free.\n" +
                "[36～37] 주어진 글 다음에 이어질 글의 순서로 가장 적절한 \n" +
                "것을 고르시오.\n" +
                "36.\n" +
                "The impact of color has been studied for decades. For \n" +
                "example, in a factory, the temperature was maintained at \n" +
                "72℉ and the walls were painted a cool blue-green. The \n" +
                "employees complained of the cold.\n" +
                "(A) The psychological effects of warm and cool hues seem to \n" +
                "be used effectively by the coaches of the Notre Dame \n" +
                "football team. The locker rooms used for half-time breaks \n" +
                "were reportedly painted to take advantage of the \n" +
                "emotional impact of certain hues.\n" +
                "(B) The home-team room was painted a bright red, which \n" +
                "kept team members excited or even angered. The \n" +
                "visiting-team room was painted a blue-green, which had a \n" +
                "calming effect on the team members. The success of this \n" +
                "application of color can be noted in the records set by \n" +
                "Notre Dame football teams.\n" +
                "(C) The temperature was maintained at the same level, but the \n" +
                "walls were painted a warm coral. The employees stopped \n" +
                "complaining about the temperature and reported they \n" +
                "were quite comfortable.\n" +
                "* hue: 색조, 색상 \n" +
                "① (A) － (C) － (B) ② (B) － (A) － (C)\n" +
                "③ (B) － (C) － (A) ④ (C) － (A) － (B) \n" +
                "⑤ (C) － (B) － (A)\n" +
                "][37.  \n" +
                "Food plays a large part in how much you enjoy the \n" +
                "outdoors. The possibilities are endless, so you can \n" +
                "constantly vary your diet.\n" +
                "(A) They walk only a few miles each day and may use the \n" +
                "same campsite for several nights. Survival eaters eat \n" +
                "some dry cereal for breakfast, and are up and walking \n" +
                "within minutes of waking.\n" +
                "(B) Wilderness dining has two extremes: gourmet eaters and \n" +
                "survival eaters. The first like to make camp at lunchtime \n" +
                "so they have several hours to set up field ovens; they bake \n" +
                "cakes and bread and cook multi-course dinners.\n" +
                "(C) They walk dozens of miles every day; lunch is a series of \n" +
                "cold snacks eaten on the move. Dinner consists of a \n" +
                "freeze-dried meal, “cooked” by pouring hot water into the \n" +
                "package.\n" +
                "① (A) － (C) － (B) ② (B) － (A) － (C)\n" +
                "③ (B) － (C) － (A) ④ (C) － (A) － (B)\n" +
                "⑤ (C) － (B) － (A)\n" +
                "[38～39] 글의 흐름으로 보아, 주어진 문장이 들어가기에 가장 \n" +
                "적절한 곳을 고르시오.\n" +
                "38.\n" +
                "The researchers had made this happen by lengthening the \n" +
                "period of daylight to which the peach trees on whose roots \n" +
                "the insects fed were exposed. \n" +
                "Exactly how cicadas keep track of time has always intrigued \n" +
                "researchers, and it has always been assumed that the insects \n" +
                "must rely on an internal clock. Recently, however, one group \n" +
                "of scientists working with the 17-year cicada in California \n" +
                "have suggested that the nymphs use an external cue and that \n" +
                "they can count. ( ① ) For their experiments they took \n" +
                "15-year-old nymphs and moved them to an experimental \n" +
                "enclosure. ( ② ) These nymphs should have taken a further \n" +
                "two years to emerge as adults, but in fact they took just one \n" +
                "year. ( ③ ) By doing this, the trees were “tricked” into \n" +
                "flowering twice during the year rather than the usual once. \n" +
                "( ④ ) Flowering in trees coincides with a peak in amino acid \n" +
                "concentrations in the sap that the insects feed on. ( ⑤ ) So it \n" +
                "seems that the cicadas keep track of time by counting the \n" +
                "peaks.\n" +
                "* nymph: 애벌레\n" +
                "** sap: 수액\n" +
                "][39.  \n" +
                "They also rated how generally extroverted those fake \n" +
                "extroverts appeared, based on their recorded voices and \n" +
                "body language.\n" +
                "Some years ago, a psychologist named Richard Lippa called \n" +
                "a group of introverts to his lab and asked them to act like \n" +
                "extroverts while pretending to teach a math class. ( ① ) Then \n" +
                "he and his team, with video cameras in hand, measured the \n" +
                "length of their strides, the amount of eye contact they made \n" +
                "with their “students,” the percentage of time they spent \n" +
                "talking, and the volume of their speech. ( ② ) Then Lippa \n" +
                "did the same thing with actual extroverts and compared the \n" +
                "results. ( ③ ) He found that although the latter group came \n" +
                "across as more extroverted, some of the fake extroverts were \n" +
                "surprisingly convincing. ( ④ ) It seems that most of us \n" +
                "know how to fake it to some extent. ( ⑤ ) Whether or not \n" +
                "we’re aware that the length of our strides and the amount of \n" +
                "time we spend talking and smiling mark us as introverts and \n" +
                "extroverts, we know it unconsciously. [3점]\n" +
                "40. 다음 글의 내용을 한 문장으로 요약하고자 한다. 빈칸 (A)와 \n" +
                "(B)에 들어갈 말로 가장 적절한 것은?\n" +
                "Plato and Tolstoy both assume that it can be firmly \n" +
                "established that certain works have certain effects. Plato is \n" +
                "sure that the representation of cowardly people makes us \n" +
                "cowardly; the only way to prevent this effect is to suppress \n" +
                "such representations. Tolstoy is confident that the artist \n" +
                "who sincerely expresses feelings of pride will pass those \n" +
                "feelings on to us; we can no more escape than we could \n" +
                "escape an infectious disease. In fact, however, the effects \n" +
                "of art are neither so certain nor so direct. People vary a \n" +
                "great deal both in the intensity of their response to art and \n" +
                "in the form which that response takes. Some people may \n" +
                "indulge fantasies of violence by watching a film instead of \n" +
                "working out those fantasies in real life. Others may be \n" +
                "disgusted by even glamorous representations of violence. \n" +
                "Still others may be left unmoved, neither attracted nor \n" +
                "disgusted.\n" +
                "\uDB80\uDC3B\n" +
                "Although Plato and Tolstoy claim that works of art have \n" +
                "a(n) (A)  impact on people’s feelings, the degrees \n" +
                "and forms of people’s actual responses (B)  greatly.\n" +
                "(A) (B) (A) (B)\n" +
                "① favorable ……differ ② direct ……converge\n" +
                "③ temporary …… fluctuate ④ unexpected ……converge\n" +
                "⑤ unavoidable……differ\n" +
                "][[41～42] 다음 글을 읽고, 물음에 답하시오.\n" +
                "Increased size affects group life in a number of ways. There \n" +
                "is evidence that larger groups (five or six members) are more \n" +
                "productive than smaller groups (two or three members). \n" +
                "Members of larger groups tend to offer more suggestions than \n" +
                "members of smaller groups, and although they seem to reach \n" +
                "less agreement, they also show less tension. These differences \n" +
                "may reflect the greater need of larger groups to solve \n" +
                "organizational problems. Members may realize that their \n" +
                "behavior must become more goal-directed, since it is unlikely \n" +
                "that they can coordinate their actions without making a special \n" +
                "effort to do so. Larger groups also put more pressure on their \n" +
                "members to conform. In such groups, it is harder for everyone \n" +
                "to take part equally in discussions or to have the same amount \n" +
                "of influence on decisions.\n" +
                "There is evidence that groups with an even number of \n" +
                "members differ from groups with an odd number of members. \n" +
                "The former disagree more than the latter and suffer more \n" +
                "deadlocks as a result. Groups with an even number of \n" +
                "members may split into halves. This is impossible in groups \n" +
                "with an odd number of members― one side always has a \n" +
                "numerical advantage. According to some researchers, the \n" +
                "number five has special significance. Groups of this size \n" +
                "usually the problems we have just outlined. \n" +
                "Moreover, they are not plagued by the fragility and tensions \n" +
                "found in groups of two or three. Groups of five rate high in \n" +
                "member satisfaction; because of the odd number of members, \n" +
                "deadlocks are unlikely when disagreements occur.\n" +
                " \n" +
                "41. 위 글의 제목으로 가장 적절한 것은?\n" +
                "① Why the Number of Group Members Counts\n" +
                "② Individuality vs. Collectivity in the Workplace \n" +
                "③ Equal Opportunities: Toward Maximum Satisfaction\n" +
                "④ How to Cope with Conflicts in Groups\n" +
                "⑤ Agreement on Group Size Pays Off!\n" +
                "42. 위 글의 빈칸에 들어갈 말로 가장 적절한 것은? [3점]\n" +
                "① probe ② mirror ③ escape\n" +
                "④ trigger ⑤ escalate\n" +
                "[43～45] 다음 글을 읽고, 물음에 답하시오.\n" +
                "(A)\n" +
                "In my office, I have a framed letter from a couple of children \n" +
                "I have never met. The letter holds a special significance for me. \n" +
                "It reads, “Dear Dr. Brown, thanks for teaching Mr. Wills to \n" +
                "teach Tisha and Kelly.” Mr. Wills is Jeremy Wills, and (a) he is \n" +
                "one of my former students. A few years back, he took my \n" +
                "positive psychology class in college.\n" +
                "][(B)\n" +
                "Before long, the reality hit Jeremy hard. Even after \n" +
                "spending many hours each day preparing lesson plans, it \n" +
                "became clear that his methods were not working. One of the \n" +
                "worst moments was when (b) he distributed a math test. \n" +
                "Many students didn’t even look at the exam. They just put \n" +
                "their heads on their desks and slept. Jeremy became so \n" +
                "stressed that he even dreaded going into his classroom.\n" +
                "(C)\n" +
                "After graduation, Jeremy joined an organization that \n" +
                "recruits future leaders to teach in low-income communities. \n" +
                "(c) He was assigned to a small school in a poor rural county \n" +
                "in North Carolina. Later, his assistant principal took note of \n" +
                "Jeremy’s high expectations and asked him to take over (d) his \n" +
                "math class. He took charge of about a dozen failing “special ed” \n" +
                "kids, and Tisha and Kelly were among them. His idealism \n" +
                "ran high, and he thought he would be able to magically fix \n" +
                "all of their problems.\n" +
                "(D)\n" +
                "Jeremy knew something had to change. (e) He then thought \n" +
                "back to my class, remembering how negative emotions can \n" +
                "drag you down, leaving positive emotions unnoticed. That’s \n" +
                "when he decided to focus more on building positive attitudes \n" +
                "within the classroom. He borrowed lessons from my positive \n" +
                "psychology class and even mentioned my name to his students. \n" +
                "As the students’ attitudes became more optimistic, their \n" +
                "confidence with math grew too. At the end of the school year, \n" +
                "80 percent of Jeremy’s students passed the state’s math test.\n" +
                "43. 주어진 글 (A)에 이어질 내용을 순서에 맞게 배열한 것으로 \n" +
                "가장 적절한 것은?\n" +
                "① (B) － (D) － (C) ② (C) － (B) － (D)\n" +
                "③ (C) － (D) － (B) ④ (D) － (B) － (C)\n" +
                "⑤ (D) － (C) － (B)\n" +
                "44. 밑줄 친 (a)～(e) 중에서 가리키는 대상이 나머지 넷과 다른 것은?\n" +
                "① (a) ② (b) ③ (c) ④ (d) ⑤ (e)\n" +
                "45. 위 글의 Jeremy에 관한 내용과 일치하지 않는 것은?\n" +
                "① 대학에서 긍정 심리학을 수강했다.\n" +
                "② 많은 시간을 들여 수업 계획서를 준비했다.\n" +
                "③ 스트레스로 인해 교실에 들어가기를 두려워했다.\n" +
                "④ 저소득 지역에서 가르칠 미래 지도자를 모집하는 단체에 가입했다.\n" +
                "⑤ 학년말에 그의 학생의 80퍼센트가 수학 시험에 불합격했다.\n" +
                "* 확인 사항\n" +
                "◦답안지의 해당란에 필요한 내용을 정확히 기입(표기)했는지 확인\n" +
                "하시오.\n" +
                "]\n" +
                "\n" +
                "Process finished with exit code 0\n";
        String reformedText = removeExceptionalText(text);
        System.out.println(reformedText);
        for (int i = 1; i <= 45; ++i) {
            System.out.println(parseProblemGroup(reformedText, i));
            System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = =\n");
            System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = =");
            Thread.sleep(300);
        }
    }

    public static String removeExceptionalText(String text) {
        Map<String, String> replaceMap = new HashMap<>();
        replaceMap.put("\\n\\]\\[", "\n");
        for (String key : replaceMap.keySet()) {
            text = text.replaceAll(key, replaceMap.get(key));
        }
        return text;
    }

    public static String parseProblem(String data, int problemNumber) {
        String regex = "(^" + problemNumber + "\\.)(()|(.))+(\\n.+)+";
        if (problemNumber != 45) {
            regex += "(?=(\\n" + (problemNumber + 1) + "\\.))";
        }
        return regexFind(Pattern.compile(regex, Pattern.MULTILINE), data);
    }

    public static String parseProblemGroup(String data, int problemNumber) {
        String regex = "(^\\[" + problemNumber + ")\\～((\\d{2}\\])|(\\d{1}\\]))(()|(.))+(()|(\\n.+))+(?=(\\n" + problemNumber + "\\.))";
        return regexFind(Pattern.compile(regex, Pattern.MULTILINE), data);
    }
}